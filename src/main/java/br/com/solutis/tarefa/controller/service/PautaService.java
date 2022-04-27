package br.com.solutis.tarefa.controller.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.PautaControleDto;
import br.com.solutis.tarefa.controller.form.PautaForm;
import br.com.solutis.tarefa.modelo.Pauta;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.utilitarios.Criptografia;

public class PautaService {

	static String pautaInexistente = "Pauta inexistente.";

	
	

	public ResponseEntity<PautaControleDto> abrirPauta(Long idDaPauta, PautaRepository pautaRepository) {

		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);
		Criptografia criptografiaDaPauta = new Criptografia();

		if (!pauta.isPresent())
			return ResponseEntity.ok().body(new PautaControleDto(false, pautaInexistente));

		if (pauta.get().isPleitoAnulado())
			return ResponseEntity.ok()
					.body(new PautaControleDto(idDaPauta, false, pauta.get().getNome(),
							"Votação de pauta anluada e não poderá ser reaberta.",
							pauta.get().getInicioDoPeriodoDeVotacao(), pauta.get().getFimDoPeriodoDeVotacao()));

		try {

			if (pauta.get().abrirVotacao()) {

				pauta.get().setPleitoAnulado(false);
				pauta.get().setHashEleicao(criptografiaDaPauta.criptografarPauta(idDaPauta, pauta.get().getNome()));
				pautaRepository.save(pauta.get());

			} else {

				return ResponseEntity.ok()
						.body(new PautaControleDto(idDaPauta, false, pauta.get().getNome(),
								pauta.get().isVotacaoEncerrada() ? "Esta Votação Já ocorreu e não poode ser reaberta"
										: "Esta pauta já está aberta para votação"));

			}

		} catch (Exception e) {

			return ResponseEntity.ok().body(new PautaControleDto(idDaPauta, false, pauta.get().getNome(),
					"Ocorreu um erro e a pauta não pode ser aberta. Retorno do servidor: " + e));

		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new PautaControleDto(idDaPauta, true, pauta.get().getNome(), "Pauta Aberta Para Votação.",
						pauta.get().getInicioDoPeriodoDeVotacao(), pauta.get().getFimDoPeriodoDeVotacao()));

	}

	public ResponseEntity<PautaControleDto> anularPauta(Long idDaPauta, PautaRepository pautaRepository) {

		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);

		if (!pauta.isPresent())
			return ResponseEntity.ok().body(new PautaControleDto(false, pautaInexistente));

		if (pauta.get().isPleitoAnulado())
			return ResponseEntity.ok()
					.body(new PautaControleDto(idDaPauta, false, pauta.get().getNome(), "Votação de pauta já anluada.",
							pauta.get().getInicioDoPeriodoDeVotacao(), pauta.get().getFimDoPeriodoDeVotacao()));

		pauta.get().setPleitoAnulado(true);
		pauta.get().setFimDoPeriodoDeVotacao(LocalDateTime.now());
		pautaRepository.save(pauta.get());

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new PautaControleDto(idDaPauta, true, pauta.get().getNome(), "Pauta Anulada",
						pauta.get().getInicioDoPeriodoDeVotacao(), pauta.get().getFimDoPeriodoDeVotacao()));

	}

	
	public ResponseEntity<PautaControleDto> processarPauta(PautaForm pautaForm,
			UriComponentsBuilder uriBuilder, PautaRepository pautaRepository) {

		if (pautaForm.getId() == null) pautaForm.setId(0L);  
		
		URI uri;
		Optional<Pauta> pauta = pautaRepository.findById(pautaForm.getId());

		if (pauta.isEmpty()) {
			Pauta pautaNova = new Pauta();
			pautaNova.atualizar(pautaForm);
			pautaRepository.save(pautaNova);
			uri = uriBuilder.path("/pauta").buildAndExpand(pautaNova.getId()).toUri();
			return ResponseEntity.created(uri).body(new PautaControleDto(true, "Pauta Criada"));
		
		}
	
		 ResponseEntity<PautaControleDto> validacoes =  promoverValidacoes(pauta);
			
	 	if(validacoes == null) {
			pauta.get().atualizar(pautaForm);
			pautaRepository.save(pauta.get());
			uri = uriBuilder.path("/pauta").buildAndExpand(pauta.get().getId()).toUri();
			return ResponseEntity.created(uri).body(new PautaControleDto(true, "Pauta Alterada."));
		
		} else {
			return validacoes;
		}

		

	}

	public ResponseEntity<PautaControleDto> excluirPauta(Long idDaPauta,
			UriComponentsBuilder uriBuilder, PautaRepository pautaRepository) {
		
		if (idDaPauta == null) idDaPauta = 0L;  
		URI uri;
		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);

		if (pauta.isEmpty())
			return ResponseEntity.ok().body(new PautaControleDto(false,
					pautaInexistente, new Pauta()));
		 
		ResponseEntity<PautaControleDto> validacoes =  promoverValidacoes(pauta);
			
		 	if(validacoes == null) {
		    	pautaRepository.delete(pauta.get());
		    	uri = uriBuilder.path("/pauta").buildAndExpand(pauta.get().getId()).toUri();
				return ResponseEntity.created(uri).body(new PautaControleDto(true, "Pauta Excluída."));
		 	} else {
				return validacoes;
			}
	}
	
	private ResponseEntity<PautaControleDto> promoverValidacoes (Optional<Pauta> pauta) {
		
		
		if (pauta.get().isPleitoAnulado())
			return ResponseEntity.ok().body(new PautaControleDto(false,
					"A votação desta pauta foi anulada. Não pode ser alterada ou excluída.", pauta.get()));

		if (pauta.get().isVotacaoEncerrada())
			return ResponseEntity.ok().body(new PautaControleDto(false,
					"A votação desta pauta já foi encerrada. Não pode ser alterada ou excluída.", pauta.get()));

		if (pauta.get().isVotacaoAberta())
			return ResponseEntity.ok().body(new PautaControleDto(false,
					"A votação desta pauta já foi aberta. Não pode ser alterada ou excluída.", pauta.get()));

		return null;
	}
	
	
	
}
