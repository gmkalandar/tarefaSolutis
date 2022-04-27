package br.com.solutis.tarefa.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.solutis.tarefa.controller.dto.AuditoriaCedulaEletronicaDto;
import br.com.solutis.tarefa.controller.dto.CedulaEletronicaDto;
import br.com.solutis.tarefa.controller.dto.ConfirmacaoVotoDto;
import br.com.solutis.tarefa.controller.form.CedulaEletronicaForm;
import br.com.solutis.tarefa.controller.service.CedulaEletronicaService;
import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.repository.CedulaEletronicaRepository;
import br.com.solutis.tarefa.repository.EleitorRepository;
import br.com.solutis.tarefa.repository.PautaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/votos")
public class CedulaEletronicaController {

	@Autowired
	private CedulaEletronicaRepository cedulaEletronicaRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private EleitorRepository eleitorRepository;

	CedulaEletronicaService cedulaEletronicaService = new CedulaEletronicaService();

	@GetMapping
	@ApiOperation(value = "Lista os votos de uma pauta pelo ID.")
	@RequestMapping("/{idDaPauta}")
	public List<CedulaEletronicaDto> buscarTodas(@PathVariable Long idDaPauta) {

		Optional<List<CedulaEletronica>> voto = Optional.of(cedulaEletronicaRepository.findByPautaId(idDaPauta));

		return CedulaEletronicaDto.valueOf(voto.get());

	}

	@GetMapping
	@ApiOperation(value = "Mecanismo de auditoria. Todo o voto computado ganha uma cripografia única, para prevenção de fraudes eletrônicas. Este métodoo faz uma auditoria desta criptografia para saber se há votos inválidoos em uma votação de uma determinada pauta.")
	@RequestMapping("/auditar/{idDaPauta}")
	public List<AuditoriaCedulaEletronicaDto> auditarVotosPorIdDaPauta(@PathVariable Long idDaPauta) {

		return cedulaEletronicaService.auditarVotosPorIdDaPauta(idDaPauta, cedulaEletronicaRepository);

	}

	@PostMapping
	@ApiOperation(value = "Registra o vooto do eleitor. Um mesmo eleitor (CPF) não pode votar duas vezes no mesmo pleito. Eleitores com CPF em situação irregular também estão proibidos de votar.")
	@Transactional
	@RequestMapping("/votar")
	public ResponseEntity<ConfirmacaoVotoDto> registrarVoto(
			@RequestBody @Valid CedulaEletronicaForm cedulaEletronicaForm, UriComponentsBuilder uriBuilder)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		
		return cedulaEletronicaService.registrarVoto(cedulaEletronicaForm, uriBuilder, cedulaEletronicaRepository,
				pautaRepository, eleitorRepository);

	}

}
