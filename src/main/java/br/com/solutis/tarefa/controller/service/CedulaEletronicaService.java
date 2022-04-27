package br.com.solutis.tarefa.controller.service;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.AuditoriaCedulaEletronicaDto;
import br.com.solutis.tarefa.controller.dto.ConfirmacaoVotoDto;
import br.com.solutis.tarefa.controller.form.CedulaEletronicaForm;
import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.modelo.Eleitor;
import br.com.solutis.tarefa.modelo.Pauta;
import br.com.solutis.tarefa.repository.CedulaEletronicaRepository;
import br.com.solutis.tarefa.repository.EleitorRepository;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.utilitarios.Criptografia;
import br.com.solutis.tarefa.utilitarios.ValidadorDeCPF;

public class CedulaEletronicaService {


	public List<AuditoriaCedulaEletronicaDto> auditarVotosPorIdDaPauta(@PathVariable Long idDaPauta,
			CedulaEletronicaRepository cedulaEletronicaRepository) {

		Optional<List<CedulaEletronica>> votosOptional = Optional
				.of(cedulaEletronicaRepository.findByPautaId(idDaPauta));

		List<CedulaEletronica> votos = votosOptional.get();
	
		return auditarVotos(votos, idDaPauta, "Voto Válido", "****VOTO ADULTERADO***");

	}

	public ResponseEntity<ConfirmacaoVotoDto> registrarVoto(CedulaEletronicaForm cedulaEletronicaForm,
			UriComponentsBuilder uriBuilder, CedulaEletronicaRepository cedulaEletronicaRepository,
			PautaRepository pautaRepository, EleitorRepository eleitorRepository)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

	
		
		
		// Variáveis e objetos

		ValidadorDeCPF validadorDeCPF = new ValidadorDeCPF();
		Long idDaPauta = cedulaEletronicaForm.getIdDaPauta();
		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);
		Optional<Eleitor> eleitor = eleitorRepository.findById(cedulaEletronicaForm.getIdDoEleitor());
		CedulaEletronica voto;
		Criptografia criptografiaDoVoto = new Criptografia();
		URI uri;
		ConfirmacaoVotoDto confirmacao;

		
		
		
		
		
		
		// Verificações de pauta e eleitor

		if (!pauta.isPresent())
			return ResponseEntity.ok().body(new ConfirmacaoVotoDto("Pauta inexistente."));

		if (!pauta.get().isVotacaoAberta())
			return ResponseEntity.ok().body(new ConfirmacaoVotoDto(pauta.get().getNome(),
					"Esta pauta ainda não teve seu período de votação iniciado. Por favor, aguarde o auditor."));

		if (pauta.get().isVotacaoEncerrada())
			return ResponseEntity.ok().body(new ConfirmacaoVotoDto(pauta.get().getNome(),
					"Período de votação para esta pauta já se esgotou. Voto não computado."));

		if (!eleitor.isPresent())
			return ResponseEntity.ok().body(new ConfirmacaoVotoDto(pauta.get().getNome(), "Eleitor não cadastrado."));

		try {

			if (!validadorDeCPF.validarCpf(String.valueOf(eleitor.get().getCpf()))) {

				return ResponseEntity.ok().body(new ConfirmacaoVotoDto(pauta.get().getNome(),
						"O CPF do eleitor encontra-se em situação irregular. Voto não computado"));

			}

		} catch (Exception e1) {

			e1.printStackTrace();

		}

		if (pauta.get().getEleitores().stream().filter(e -> e.getCpf().equals(eleitor.get().getCpf())).count() > 0) {

			return ResponseEntity.ok().body(new ConfirmacaoVotoDto(pauta.get().getNome(),
					"Um eleitor com este CPF já votou nesta pauta. Voto não computado."));

		}

		
		
		
		
		
		
		// Criptografia e persistênca dos dados

		voto = cedulaEletronicaForm.valueOf(pautaRepository);

		voto.setHashVoto(
				criptografiaDoVoto.criptografarVoto(voto.getDataDoVoto(), voto.isValorDoVoto().name(), idDaPauta));

		pauta.get().getVotos().add(voto);

		pauta.get().getEleitores().add(eleitor.get());

		cedulaEletronicaRepository.save(voto);
		pautaRepository.save(pauta.get());

		
		
		
		
		
		// resposta

		confirmacao = new ConfirmacaoVotoDto(voto);
		confirmacao.setMensagem("Voto Computado.");
		uri = uriBuilder.path("/votos").buildAndExpand(voto.getId()).toUri();
		return ResponseEntity.created(uri).body(confirmacao);

	}
	
	
	
	
	
	
	
	public List<AuditoriaCedulaEletronicaDto> auditarVotos(List<CedulaEletronica> votos, Long idDaPauta, 
			String mensagemdeVotoValido, String mensagemdeVotoInvalido) {
		
	
		Criptografia criptografiaDoVoto = new Criptografia();
		Map<Long, AuditoriaCedulaEletronicaDto> votosDto = new HashMap<>();
		votos.stream().forEach(v -> {

		String hashDeVerificacao = criptografiaDoVoto.montarStringParaVoto(v.getDataDoVoto(),
				v.isValorDoVoto().name(), idDaPauta);
		String resultado = "";

		try {
			
			resultado = criptografiaDoVoto.validarCriptografiaPBKDF2(hashDeVerificacao, v.getHashVoto())
					? mensagemdeVotoValido
					: mensagemdeVotoInvalido;
		
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		votosDto.put(v.getId(), new AuditoriaCedulaEletronicaDto(resultado, v)); 

	});

	return new ArrayList<>(votosDto.values()); 
	
	}
	

}
