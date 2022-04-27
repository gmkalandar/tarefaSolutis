package br.com.solutis.tarefa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.EleitorControleDto;
import br.com.solutis.tarefa.controller.dto.EleitorDto;
import br.com.solutis.tarefa.controller.form.EleitorCadastroForm;
import br.com.solutis.tarefa.controller.form.EleitorCpfForm;
import br.com.solutis.tarefa.controller.form.EleitorNomeForm;
import br.com.solutis.tarefa.controller.service.EleitorService;
import br.com.solutis.tarefa.modelo.Eleitor;
import br.com.solutis.tarefa.repository.EleitorRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/eleitor")
public class EleitorController {

	@Autowired
	private EleitorRepository eleitorRepository;
		
	EleitorService eleitorService = new EleitorService();

	@GetMapping
	@ApiOperation(value = "Busca por todos os eleitores, sem critério, por ordem alfabética.")
	public Page<EleitorDto> buscarTodos(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		Page<Eleitor> eleitor = eleitorRepository.findAll(paginacao);

		return EleitorDto.valueOf(eleitor);
	}

	@GetMapping
	@ApiOperation(value = "Busca o eleitor pelo ID.")
	@RequestMapping("/id/{idDoEleitor}")
	public Page<EleitorDto> buscarEleitoorPorId(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@PathVariable Long idDoEleitor) {

		Page<Eleitor> eleitor = eleitorRepository.findById(idDoEleitor, paginacao);

		return EleitorDto.valueOf(eleitor);
	}

	@GetMapping
	@ApiOperation(value = "Busca pelos eleitores de uma determinada pauta pelo id da mesma.")
	@RequestMapping("/idpauta/{idDaPauta}")
	public Page<EleitorDto> buscarPeloIdDaPauta(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@PathVariable Long idDaPauta) {

		Page<Eleitor> eleitor = eleitorRepository.buscarEleitorPeloIdDaPauta(idDaPauta, paginacao);

		return EleitorDto.valueOf(eleitor);
	}

	@PostMapping
	@ApiOperation(value = "Busca o oeleitor pelo nome. Digitandoo uma parte do nome, uma lista daqueles que contenham o escopo commo parte do nome é trazida.")
	@RequestMapping("/nome")
	public Page<EleitorDto> buscarEleitorPorNome(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@RequestBody @Valid EleitorNomeForm eleitorNomeForm) {

		Page<Eleitor> eleitor = eleitorRepository.buscarEleitorPeloNome(eleitorNomeForm.getNome().toUpperCase(),
				paginacao);

		return EleitorDto.valueOf(eleitor);

	}

	@PostMapping
	@ApiOperation(value = "Pesquisa o Eleitor por CPF.")
	@RequestMapping("/cpf")
	public Page<EleitorDto> buscarEleitorPorCpf(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@RequestBody @Valid EleitorCpfForm eleitorCpfForm) {

		Page<Eleitor> eleitor = eleitorRepository.findByCpf(eleitorCpfForm.getCpf(), paginacao);

		return EleitorDto.valueOf(eleitor);

	}

	@PostMapping
	@ApiOperation(value = "Inclui ou atualiza um novo eleitor. Não pode haver CPF repetido.")
	@RequestMapping("/salvar")
	@Transactional
	public ResponseEntity<EleitorControleDto> incluirEleitor(
			@RequestBody @Valid EleitorCadastroForm eleitorCadastroForm, UriComponentsBuilder uriBuilder) {

		return eleitorService.processarEleitor(eleitorCadastroForm, uriBuilder, eleitorRepository);
	
	}
	
	
	@DeleteMapping
	@ApiOperation(value = "Exclui um eleitor. A Exclusão é negada caso ele já tenha participado de alguma eleição, para fins de registro.")
	@RequestMapping("/excluir/{idDoEleitor}")
	@Transactional
	public ResponseEntity<EleitorControleDto> excluirEleitor(@PathVariable Long idDoEleitor,
			UriComponentsBuilder uriBuilder) {
		
		return eleitorService.excluirEleitor(idDoEleitor, uriBuilder, eleitorRepository);

	}

	
	
}
