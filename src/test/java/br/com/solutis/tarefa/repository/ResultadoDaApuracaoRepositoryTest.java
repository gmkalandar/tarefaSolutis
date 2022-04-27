package br.com.solutis.tarefa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.*;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;

@RunWith(SpringRunner.class)
@DataJpaTest
class ResultadoDaApuracaoRepositoryTest {

	
	@Autowired
	private ResultadoDaApuracaoRepository resultadoDaApuracaoRepository;
	
	
	@Test
	void deveriaTrazerApuracaoPeloIdDaPauta() {
		
		Long idDaPauta= 1L;
		List<ResultadoDaApuracao> resultadoDaApuracao =  resultadoDaApuracaoRepository.findByPautaId(idDaPauta);
		Assert.assertNotNull(resultadoDaApuracao);
		
	}

}
