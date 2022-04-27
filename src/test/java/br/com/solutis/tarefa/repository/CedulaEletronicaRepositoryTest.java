package br.com.solutis.tarefa.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import br.com.solutis.tarefa.modelo.CedulaEletronica;
import org.junit.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CedulaEletronicaRepositoryTest {

	@Autowired
	private CedulaEletronicaRepository cedulaEletronicaRepository;
	
	
	@Test
	public void deveriaTrazerlistaDeVotosPeloIdDaPauta() {
		
		Long idDaPauta = 1L;
		List<CedulaEletronica> votos = cedulaEletronicaRepository.findByPautaId(idDaPauta);
		Assert.assertNotNull(votos);
		
		
	}

}
