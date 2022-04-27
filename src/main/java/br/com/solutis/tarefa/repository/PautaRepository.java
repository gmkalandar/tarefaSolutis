package br.com.solutis.tarefa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solutis.tarefa.modelo.Pauta;


public interface PautaRepository extends JpaRepository<Pauta, Long> {
	

@Query(value = "SELECT * FROM PAUTA WHERE UPPER(NOME) LIKE %:nome%",  nativeQuery = true)	
List<Pauta>	buscarPeloNome(@Param("nome") String nome);


static String QUERYELEITOR =  "SELECT p.*  FROM PAUTA p "
							+ "INNER JOIN  PAUTA_ELEITORES T ON p.ID = T.PAUTA_ID "
					     	+ "INNER JOIN ELEITOR e ON T.ELEITORES_ID = e.ID "
					     	+ "WHERE e.ID = :idDoEleitor";

@Query(value = QUERYELEITOR,  nativeQuery = true)
List<Pauta> buscarPautasPorEleitor(@Param("idDoEleitor") Long idDoEleitor);


	
}
