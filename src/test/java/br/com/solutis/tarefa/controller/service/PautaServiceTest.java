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
import br.com.solutis.tarefa.controller.form.PautaForm;
import br.com.solutis.tarefa.controller.form.ResultadoDaApuracaoForm;
import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;
import br.com.solutis.tarefa.repository.PautaRepository;
import br.com.solutis.tarefa.repository.ResultadoDaApuracaoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

class PautaServiceTest {

	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Test
	void deveriaAbrirAPauta() {
		PautaService pautaService = new PautaService();
		Long idDaPauta = 1L;
		ResponseEntity<PautaControleDto> pautaControleDto =  pautaService.abrirPauta(idDaPauta, pautaRepository);
		Assert.assertNotNull(pautaControleDto);
	}

	
	@Test
	void deveriaAnularAPauta() {
		PautaService pautaService = new PautaService();
		Long idDaPauta = 1L;
		ResponseEntity<PautaControleDto> pautaControleDto =  pautaService.anularPauta(idDaPauta, pautaRepository);
		Assert.assertNotNull(pautaControleDto);
	}
	
	
	
	@Test
	void deveriaCriarAPauta() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		PautaService pautaService = new PautaService();
		PautaForm pautaForm = new PautaForm();
		pautaForm.setDuracao(4);
		pautaForm.setNome("Teste");
		ResponseEntity<PautaControleDto> pautaControleDto =  pautaService.processarPauta(pautaForm, uriBuilder, pautaRepository);
		Assert.assertNotNull(pautaControleDto);
	}
	
	
	@Test
	void deveriaAtualizarAPauta() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		PautaService pautaService = new PautaService();
		PautaForm pautaForm = new PautaForm();
		pautaForm.setId(3L);
		pautaForm.setDuracao(4);
		pautaForm.setNome("Teste");
		ResponseEntity<PautaControleDto> pautaControleDto =  pautaService.processarPauta(pautaForm, uriBuilder, pautaRepository);
		Assert.assertNotNull(pautaControleDto);
	}
	
	
	@Test
	void deveriaExcluirAPauta() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		PautaService pautaService = new PautaService();
		Long idDaPauta = 3L;
		ResponseEntity<PautaControleDto> pautaControleDto =  pautaService.excluirPauta(idDaPauta, uriBuilder, pautaRepository);
		Assert.assertNotNull(pautaControleDto);
	} 
}
