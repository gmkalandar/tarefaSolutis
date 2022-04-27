package br.com.solutis.tarefa.controller.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import br.com.solutis.tarefa.controller.dto.PautaControleDto;
import br.com.solutis.tarefa.controller.dto.ResultadoDaApuracaoDto;
import br.com.solutis.tarefa.controller.form.ResultadoDaApuracaoForm;
import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.repository.ResultadoDaApuracaoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

class ResultadoDaApuracaoServiceTest {

	@Autowired
	private ResultadoDaApuracaoRepository resultadoDaApuracaoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Test
	void deveriaFazerApuracaoPeloIdDaPauta() {

		ResultadoDaApuracaoForm resultadoDaApuracaoForm = new ResultadoDaApuracaoForm();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		ResultadoDaApuracaoService resultadoDaApuracaoService = new ResultadoDaApuracaoService();
		Long idDaPauta = 2L;
		ResponseEntity<ResultadoDaApuracaoDto> resultadoDaApuracaoDto = new ResponseEntity<ResultadoDaApuracaoDto>(HttpStatus.CONTINUE);
		resultadoDaApuracaoForm.setIdDaPauta(idDaPauta);

		try {
			resultadoDaApuracaoDto = resultadoDaApuracaoService.apurarVotacaoDePauta(resultadoDaApuracaoForm,
					uriBuilder, resultadoDaApuracaoRepository, pautaRepository);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

			e.printStackTrace();
		}

		Assert.assertNotNull(resultadoDaApuracaoDto);

	}

	@Test
	void deveriaTrazerApuracaoPeloIdDaPauta() {

		
		ResultadoDaApuracaoService resultadoDaApuracaoService = new ResultadoDaApuracaoService();
		Long idDaPauta = 1L;

		List<ResultadoDaApuracaoDto> resultadoDaApuracaoDto = resultadoDaApuracaoService
				.buscarApuracaoPeloIdDaPauta(idDaPauta, resultadoDaApuracaoRepository);
		Assert.assertNotNull(resultadoDaApuracaoDto);

	}
}
