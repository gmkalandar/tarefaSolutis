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

import br.com.solutis.tarefa.modelo.Eleitor;

@RunWith(SpringRunner.class)
@DataJpaTest
 class EleitorRepositoryTest {

	@Autowired
	private EleitorRepository eleitorRepository;

	@Test
	 void deveriaTrazerListaDeEleitoresPeloNome() {
		
		Pageable paginacao = PageRequest.of(1, 10);
		String nome = "Jair";
		Page<Eleitor> eleitor = eleitorRepository.buscarEleitorPeloNome(nome, paginacao);
		Assert.assertNotNull(eleitor);
		
	}

	@Test
	 void deveriaTrazerListaDeEleitoresPeloId() {
		
		Pageable paginacao = PageRequest.of(1, 10);
		Long idDaPauta= 1L;
		Page<Eleitor> eleitor = eleitorRepository.buscarEleitorPeloIdDaPauta(idDaPauta, paginacao);
		Assert.assertNotNull(eleitor);
		
	}
	
	
	@Test
	 void deveriaTrazerOEleitoresPeloId() {
		
		Pageable paginacao = PageRequest.of(1, 10);
		Long IdDoEleitor = 1L;
		Page<Eleitor> eleitor = eleitorRepository.findById(IdDoEleitor, paginacao);
		Assert.assertNotNull(eleitor);
		
	}
	
	
	
	@Test
	 void deveriaTrazerOEleitoresPeloCpf() {
		
		Pageable paginacao = PageRequest.of(1, 10);
		Long cpf = 9876543210L;
		Page<Eleitor> eleitor = eleitorRepository.findByCpf(cpf, paginacao);
		Assert.assertNotNull(eleitor);
		
	}
	
	 
	
	 @Test
	 void deveriaTrazerOEleitoresPeloCpfOptional() {
		
		
		Long cpf = 9876543210L;
		Optional<Eleitor> eleitor = eleitorRepository.findByCpf(cpf);
		Assert.assertNotNull(eleitor);
		
	}
	
	

	 @Test
	 void deveriaTrazerOEleitoresPeloCpfLong() {
		
		Long cpf = 9876543210L;
		List<Long> eleitor = eleitorRepository.buscarIdDoEleitorNasPautas(cpf);
		Assert.assertNotNull(eleitor);
		
	}
	
}