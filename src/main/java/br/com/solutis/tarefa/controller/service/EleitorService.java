package br.com.solutis.tarefa.controller.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.EleitorControleDto;
import br.com.solutis.tarefa.controller.form.EleitorCadastroForm;
import br.com.solutis.tarefa.modelo.Eleitor;
import br.com.solutis.tarefa.repository.EleitorRepository;

public class EleitorService {


	public ResponseEntity<EleitorControleDto> processarEleitor(EleitorCadastroForm eleitorCadastroForm,
			UriComponentsBuilder uriBuilder, EleitorRepository eleitorRepository) {

		URI uri;
		String acao = "Atualizado";

		if (eleitorCadastroForm.getId() == null || eleitorCadastroForm.getId() == 0L) {

			eleitorCadastroForm.setId(0L);
			acao = "Incluído";
		
		}

		Eleitor eleitor = new Eleitor();

		eleitor.atualizar(eleitorCadastroForm);
		
		Optional<Eleitor> eleitorBanco = eleitorRepository.findByCpf(eleitorCadastroForm.getCpf());
		if (eleitorBanco.isPresent())
			return ResponseEntity.ok().body(new EleitorControleDto(false, "Este CPF já se encontra cadastrado."));
		
		eleitorRepository.save(eleitor);

		uri = uriBuilder.path("/eleitor").buildAndExpand(eleitor.getId()).toUri();
		return ResponseEntity.created(uri).body(new EleitorControleDto(true, "Eleitor " + acao, eleitor));

	}
	
	
	
	
	
	public  ResponseEntity<EleitorControleDto> excluirEleitor(Long idDoEleitor, UriComponentsBuilder uriBuilder, 
			EleitorRepository eleitorRepository) {
	
		URI uri;
		Optional<Eleitor> eleitor = eleitorRepository.findById(idDoEleitor);
		 

		if (eleitor.isEmpty())
			return ResponseEntity.ok().body(new EleitorControleDto(false, "Eleitor inexistente."));
		
			
		List<Long> eleitorIds  = eleitorRepository.buscarIdDoEleitorNasPautas(idDoEleitor);
		
		
		if (!eleitorIds.isEmpty())
			return ResponseEntity.ok().body(new EleitorControleDto(false, "Eleitor consta listado em votações de outras pautas. Não pode ser excluído."));
		
				
		eleitorRepository.delete(eleitor.get());

		uri = uriBuilder.path("/eleitor").buildAndExpand(eleitor.get().getId()).toUri();
		return ResponseEntity.created(uri).body(new EleitorControleDto(true, "Eleitor Excluído.", eleitor.get()));

		
	}

}
