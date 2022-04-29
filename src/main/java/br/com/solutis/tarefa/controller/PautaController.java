package br.com.solutis.tarefa.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.solutis.tarefa.controller.dto.PautaControleDto;
import br.com.solutis.tarefa.controller.dto.PautaDto;
import br.com.solutis.tarefa.controller.form.PautaForm;
import br.com.solutis.tarefa.controller.form.PautaNomeForm;
import br.com.solutis.tarefa.controller.service.PautaService;
import br.com.solutis.tarefa.modelo.Pauta;
import br.com.solutis.tarefa.repository.PautaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	

	@Autowired
	private PautaRepository pautaRepository;

	PautaService pautaService = new PautaService();

	@GetMapping
	@ApiOperation(value = "Lista Todas as pautas, sem critério.")
	public List<PautaDto> buscarTodas() {

		List<Pauta> pautas = pautaRepository.findAll();

		return PautaDto.valueOf(pautas);
	}
 
	@GetMapping
	@ApiOperation(value = "Procura por uma única pauta, pelo Id.")
	@RequestMapping("/id/{idDaPauta}")
	public PautaDto buscarTodasPorId(@PathVariable Long idDaPauta) {

		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);

		if (!pauta.isPresent())
			return new PautaDto();

		return new PautaDto(pauta.get());

	}
	
	
	@GetMapping
	@ApiOperation(value = "Procura por pautas que um eleitor específico já tenha participado.")
	@RequestMapping("/poreleitor/{idDoEleitor}")
	public List<PautaDto> buscarEleitorNasPautas(@PathVariable Long idDoEleitor) {

		Optional<List<Pauta>> pauta = Optional.of(pautaRepository.buscarPautasPorEleitor(idDoEleitor));	

		return PautaDto.valueOf(pauta.get());

	}
	

	@PostMapping
	@ApiOperation(value = "Procura pauta(s) pelo nome. Escrevendo parte do nome, o método traz uma lista de pautas com nomes que contenham a expressão do escopo.")
	@RequestMapping("/nome")
	public List<PautaDto> buscarTodasPorNome(@RequestBody @Valid PautaNomeForm pautaNomeForm) {

		Optional<List<Pauta>> pautas = Optional
				.of(pautaRepository.buscarPeloNome(pautaNomeForm.getNome().toUpperCase()));

		return PautaDto.valueOf(pautas.get());

	}

	@GetMapping
	@RequestMapping("/abrir/{idDaPauta}")
	@ApiOperation(value = "Abre o período de Votação de uma pauta. Somente pautas inativas podem ser abertas. Uma vez aberta para votação, ela não pode mais ser alterada, e ganha uma criptografia de proteção de conteúdo.")
	@Transactional
	public ResponseEntity<PautaControleDto> abrirPauta(@PathVariable Long idDaPauta) {

		return pautaService.abrirPauta(idDaPauta, pautaRepository);

	}

	@GetMapping
	@RequestMapping("/anular/{idDaPauta}")
	@ApiOperation(value = "Anula a pauta em questão. Uma vez anulada, ela não pode ser nem editada, e nem sua sessão de votação pode ser reaberta. Usada para cancelamento emergencial do pleito.")
	@Transactional
	public ResponseEntity<PautaControleDto> anularPauta(@PathVariable Long idDaPauta) {

		return pautaService.anularPauta(idDaPauta, pautaRepository);

	}

	@PostMapping
	@RequestMapping("/salvar")
	@ApiOperation(value = "Cria ou muda o conteúdo de uma pauta. Somente pautas inativas podem ser alteradas. Pautas abertas, anuladas ou encerradas, não podem ser alteradas. Novas pautas nascem com status de inativa.")
	@Transactional
	public ResponseEntity<PautaControleDto> criarPauta(@RequestBody @Valid PautaForm pautaForm,
			UriComponentsBuilder uriBuilder) {

		return pautaService.processarPauta(pautaForm, uriBuilder, pautaRepository);
	}
	
	@DeleteMapping
	@RequestMapping("/excluir/{idDaPauta}")
	@ApiOperation(value = "Exclui uma pauta. Somente pautas inativas podem ser excluídas.")
	@Transactional
	public ResponseEntity<PautaControleDto> excluirPauta(@PathVariable Long idDaPauta,
			UriComponentsBuilder uriBuilder) {
		
		return pautaService.excluirPauta(idDaPauta, uriBuilder, pautaRepository);
	}

}
