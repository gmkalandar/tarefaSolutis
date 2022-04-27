package br.com.solutis.tarefa.controller.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

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

import br.com.solutis.tarefa.controller.dto.AuditoriaCedulaEletronicaDto;
import br.com.solutis.tarefa.controller.dto.ConfirmacaoVotoDto;
import br.com.solutis.tarefa.controller.form.CedulaEletronicaForm;
import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.modelo.ValorDoVoto;
import br.com.solutis.tarefa.repository.CedulaEletronicaRepository;
import br.com.solutis.tarefa.repository.EleitorRepository;
import br.com.solutis.tarefa.repository.PautaRepository;


@RunWith(SpringRunner.class)
@DataJpaTest

class CedulaEletronicaServiceTest {

	
	@Autowired
	private CedulaEletronicaRepository cedulaEletronicaRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private EleitorRepository eleitorRepository;
	
	CedulaEletronicaService cedulaEletronicaService = new CedulaEletronicaService();
	
	
	
	
	
	@Test
	void deveriaAuditarVotosPeloIdDaPauta() {
	
		Long idDaPauta = 1L;
		
		List<AuditoriaCedulaEletronicaDto> auditoriaCedulaEletronicaDto = cedulaEletronicaService.auditarVotosPorIdDaPauta(idDaPauta,
					 cedulaEletronicaRepository);
		
		Assert.assertNotNull(auditoriaCedulaEletronicaDto);
	}

	
	
	
	@Test
	void deveriaRegistrarVoto() throws NoSuchAlgorithmException, InvalidKeySpecException {
	
		CedulaEletronicaForm cedulaEletronicaForm  = new CedulaEletronicaForm();
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		cedulaEletronicaForm.setIdDaPauta(5L);
		cedulaEletronicaForm.setIdDoEleitor(1L);
		cedulaEletronicaForm.setValorDoVoto(ValorDoVoto.SIM);
		
		ResponseEntity<ConfirmacaoVotoDto>  confirmacaoVotoDto =  cedulaEletronicaService.registrarVoto(cedulaEletronicaForm,
			uriBuilder, cedulaEletronicaRepository,
			 pautaRepository,  eleitorRepository);
		
		Assert.assertNotNull(confirmacaoVotoDto);
	
	}
	
	
	@Test
	void deveriaAuditarVotosInterno() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Long idDaPauta = 1L;
		
		Optional<List<CedulaEletronica>> votosOptional = Optional
				.of(cedulaEletronicaRepository.findByPautaId(idDaPauta));

		List<CedulaEletronica> votos = votosOptional.get();
	
		
		
		 List<AuditoriaCedulaEletronicaDto> auditoriaCedulaEletronicaDto = cedulaEletronicaService.auditarVotos(votos, idDaPauta, "Voto Válido", "****VOTO ADULTERADO***");
		
		 Assert.assertNotNull(auditoriaCedulaEletronicaDto);
	}
	
	
	
	
}
