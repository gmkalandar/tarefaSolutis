package br.com.solutis.tarefa.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

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

import br.com.solutis.tarefa.controller.dto.ResultadoDaApuracaoDto;
import br.com.solutis.tarefa.controller.form.ResultadoDaApuracaoForm;
import br.com.solutis.tarefa.controller.service.ResultadoDaApuracaoService;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.repository.ResultadoDaApuracaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apuracao")
public class ResultadoDaApuracaoController {

	@Autowired
	private ResultadoDaApuracaoRepository resultadoDaApuracaoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	ResultadoDaApuracaoService resultadoDaApuracaoService = new ResultadoDaApuracaoService();

	@GetMapping
	@ApiOperation(value = "Consulta os resultados de apuração de um pleito.")
	@RequestMapping("/consultar/{idDaPauta}")
	public List<ResultadoDaApuracaoDto> buscarApuracaoPeloIdDaPauta(@PathVariable Long idDaPauta) {

		return resultadoDaApuracaoService.buscarApuracaoPeloIdDaPauta(idDaPauta, resultadoDaApuracaoRepository);

	}

	@PostMapping
	@ApiOperation(value = "Faz apuração de votos de uma pauta. Se a votação ainda estiver aberta, ele fará a apuração parcial. Também faz a auditoria do pleito em tempo real.")
	@Transactional
	public ResponseEntity<ResultadoDaApuracaoDto> apurarVotacaoDePauta(
			@RequestBody @Valid ResultadoDaApuracaoForm resultadoDaApuracaoForm, UriComponentsBuilder uriBuilder)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		return resultadoDaApuracaoService.apurarVotacaoDePauta(resultadoDaApuracaoForm, uriBuilder,
				resultadoDaApuracaoRepository, pautaRepository);

	}

}
