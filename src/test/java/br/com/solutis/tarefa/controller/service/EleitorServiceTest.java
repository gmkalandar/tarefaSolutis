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

import br.com.solutis.tarefa.controller.dto.EleitorControleDto;
import br.com.solutis.tarefa.controller.form.EleitorCadastroForm;
import br.com.solutis.tarefa.repository.EleitorRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
class EleitorServiceTest {


	@Autowired
	private EleitorRepository eleitorRepository;
	
	
	@Test
	void deveriaIncluirEleitor() {
		
		EleitorService eleitorService = new EleitorService();
		EleitorCadastroForm eleitorCadastroForm = new EleitorCadastroForm();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		eleitorCadastroForm.setCpf(12345678901L);
		eleitorCadastroForm.setNome("teste eleitor");
		
		ResponseEntity<EleitorControleDto> eleitorControleDto =  eleitorService.processarEleitor(eleitorCadastroForm,
				uriBuilder, eleitorRepository); 
		
		Assert.assertNotNull(eleitorControleDto);
	}
	
	
	@Test
	void deveriaEditarEleitor() {
		
		EleitorService eleitorService = new EleitorService();
		EleitorCadastroForm eleitorCadastroForm = new EleitorCadastroForm();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		eleitorCadastroForm.setId(1L);
		eleitorCadastroForm.setCpf(12345678901L);
		eleitorCadastroForm.setNome("teste eleitor");

		ResponseEntity<EleitorControleDto> eleitorControleDto =  eleitorService.processarEleitor(eleitorCadastroForm,
				uriBuilder, eleitorRepository); 
		
		Assert.assertNotNull(eleitorControleDto);
	}
	
	@Test
	void deveriaExcluirEleitor() {
		
		EleitorService eleitorService = new EleitorService();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		Long idDoEleitor = 1L;
		
		ResponseEntity<EleitorControleDto> eleitorControleDto =  eleitorService.excluirEleitor(idDoEleitor,
				uriBuilder, eleitorRepository); 
		
		Assert.assertNotNull(eleitorControleDto);
	}

}
