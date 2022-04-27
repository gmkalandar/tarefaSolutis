package br.com.solutis.tarefa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

import br.com.solutis.tarefa.modelo.Pauta;

@RunWith(SpringRunner.class)
@DataJpaTest
class PautaRepositoryTest {

	@Autowired
	private PautaRepository pautaRepository;

	@Test
	void deveriaBuscarPautaPeloNome() {

		String nome = "teste";
		List<Pauta> pauta = pautaRepository.buscarPeloNome(nome);
		Assert.assertNotNull(pauta);
	}

	@Test
	void deveriaBuscarPautaPeloIdDoEleitor() {
		
		Long idDoEleitor = 1L;
		List<Pauta> pauta = pautaRepository.buscarPautasPorEleitor(idDoEleitor);
		Assert.assertNotNull(pauta);
	
	}
}
