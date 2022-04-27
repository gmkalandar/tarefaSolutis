package br.com.solutis.tarefa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;

public interface ResultadoDaApuracaoRepository extends JpaRepository<ResultadoDaApuracao, Long>{

	List<ResultadoDaApuracao> findByPautaId(Long idDaPauta);
	

}
