package br.com.solutis.tarefa.controller.service;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.AuditoriaCedulaEletronicaDto;
import br.com.solutis.tarefa.controller.dto.ResultadoDaApuracaoDto;
import br.com.solutis.tarefa.controller.form.ResultadoDaApuracaoForm;
import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.modelo.Pauta;
import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;
import br.com.solutis.tarefa.modelo.StatusDaApuracao;
import br.com.solutis.tarefa.modelo.ValorDoVoto;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.repository.ResultadoDaApuracaoRepository;
import br.com.solutis.tarefa.utilitarios.Criptografia;

public class ResultadoDaApuracaoService {
	
	
	

	public List<ResultadoDaApuracaoDto> buscarApuracaoPeloIdDaPauta(Long idDaPauta,
			ResultadoDaApuracaoRepository resultadoDaApuracaoRepository) {

		Optional<List<ResultadoDaApuracao>> resultadoDaApuracao = Optional
				.of(resultadoDaApuracaoRepository.findByPautaId(idDaPauta));

		List<ResultadoDaApuracao> resultadoDaApuracaoList = resultadoDaApuracao.get().stream()
				.sorted(Comparator.comparing(ResultadoDaApuracao::getDataDaApuracao).reversed()).toList();

		return ResultadoDaApuracaoDto.valueOf(resultadoDaApuracaoList);

	}

	
	
	
	
	public ResponseEntity<ResultadoDaApuracaoDto> apurarVotacaoDePauta(
			@RequestBody @Valid ResultadoDaApuracaoForm resultadoDaApuracaoForm, UriComponentsBuilder uriBuilder,
			ResultadoDaApuracaoRepository resultadoDaApuracaoRepository, PautaRepository pautaRepository)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

	
		
		
		// Variáveis e Objetos

		Long idDaPauta = resultadoDaApuracaoForm.getIdDaPauta();
		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);
		Long contagem;
		Criptografia criptografiaDaPauta = new Criptografia();
		URI uri;
		ResultadoDaApuracao resultadoDaApuracao = new ResultadoDaApuracao();
		List<CedulaEletronica> votosSim;
		List<CedulaEletronica> votosNao;
		String hashPauta;
		String hashPautaArmazenada;
		ResultadoDaApuracaoDto resultadoDaApuracaoDto;
		Long quantidadeVotosSim;
		Long quantidadeVotosNao;
		Map<String, List<CedulaEletronica>> resultado;
		Map<Object, List<AuditoriaCedulaEletronicaDto>> resultadoDaAuditoria;
        CedulaEletronicaService cedulaEletronicaService = new CedulaEletronicaService();
		List<AuditoriaCedulaEletronicaDto> votosInvalidos;
		List<AuditoriaCedulaEletronicaDto> votosAuditados;
		
		
		
		// Validações

		if (!pauta.isPresent())
			return ResponseEntity.ok().body(new ResultadoDaApuracaoDto("Pauta inexistente."));

		if (!pauta.get().isVotacaoAberta())
			return ResponseEntity.ok().body(new ResultadoDaApuracaoDto(pauta.get().getNome(),
					"Esta pauta ainda não teve seu período de votação iniciado. Por favor, aguarde o auditor."));

		if (pauta.get().getVotos().isEmpty()) {
			return ResponseEntity.ok()
					.body(new ResultadoDaApuracaoDto(pauta.get().getNome(), "Esta pauta não possui votos computados."));
		}

		
		
		
		// Apuração

		contagem = pauta.get().getVotos().stream().count();

		resultadoDaApuracao.setTotaldeVotos(contagem);

		hashPautaArmazenada = pauta.get().getHashEleicao();
		resultado = pauta.get().getVotos().stream().collect(Collectors.groupingBy(v -> v.isValorDoVoto().name()));

		votosSim = resultado.get(ValorDoVoto.SIM.name());
		votosNao = resultado.get(ValorDoVoto.NÃO.name());

		quantidadeVotosSim = votosSim != null ? votosSim.stream().count() : 0L;
		quantidadeVotosNao = votosNao != null ? votosNao.stream().count() : 0L;

		resultadoDaApuracao.setDataDaApuracao(LocalDateTime.now());

		resultadoDaApuracao.setVotosSim(quantidadeVotosSim);
		resultadoDaApuracao.setVotosNao(quantidadeVotosNao);

		if (quantidadeVotosSim.equals(quantidadeVotosNao)) {
			resultadoDaApuracao.setOpcaoVencedora("EMPATE");
		} else {
			resultadoDaApuracao.setOpcaoVencedora(
					quantidadeVotosSim > quantidadeVotosNao ? ValorDoVoto.SIM.name() : ValorDoVoto.NÃO.name());
		}

		resultadoDaApuracao.setPorcentagemVotosSim(((quantidadeVotosSim * 100.0) / contagem));
		resultadoDaApuracao.setPorcentagemVotosNao(((quantidadeVotosNao * 100.0) / contagem));

				
		
		
		
		// Auditoria

		resultadoDaApuracao.setVotosAdulterados(0L);
		votosAuditados = cedulaEletronicaService.auditarVotos(pauta.get().getVotos(), idDaPauta, "1", "0");
		resultadoDaAuditoria = votosAuditados.stream().collect(Collectors.groupingBy(AuditoriaCedulaEletronicaDto::getValidadeDoVoto));
		votosInvalidos = resultadoDaAuditoria.get("0"); 
        resultadoDaApuracao.setVotosAdulterados(votosInvalidos == null ? 0L : votosInvalidos.stream().count());
		resultadoDaApuracao.setValidadeDosVotos(resultadoDaApuracao.getVotosAdulterados() == 0 ? true : false);
		hashPauta = criptografiaDaPauta.montarStringParaPauta(idDaPauta, pauta.get().getNome());
		resultadoDaApuracao
				.setValidadeDaPauta(criptografiaDaPauta.validarCriptografiaPBKDF2(hashPauta, hashPautaArmazenada));
		resultadoDaApuracao.setStatusDaApuracao(
				pauta.get().isVotacaoEncerrada() ? StatusDaApuracao.FINAL : StatusDaApuracao.PARCIAL);

		
		
		
		// Persistência

		resultadoDaApuracao.setPauta(pauta.get());
		pauta.get().getResultadosDaApuracao().add(resultadoDaApuracao);
		resultadoDaApuracaoRepository.save(resultadoDaApuracao);
		pautaRepository.save(pauta.get());

		
		
		
		
		// Resposta

		resultadoDaApuracaoDto = new ResultadoDaApuracaoDto(resultadoDaApuracao);
		uri = uriBuilder.path("/pauta").buildAndExpand(pauta.get().getId()).toUri();
		return ResponseEntity.created(uri).body(resultadoDaApuracaoDto);

	}

}
