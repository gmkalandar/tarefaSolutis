package br.com.solutis.tarefa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solutis.tarefa.modelo.CedulaEletronica;

public interface CedulaEletronicaRepository extends JpaRepository<CedulaEletronica, Long>{

		
	List<CedulaEletronica> findByPautaId(Long idDaPauta);
	

}
