package br.com.solutis.tarefa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solutis.tarefa.modelo.Eleitor;

public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
	
@Query(value = "SELECT * FROM ELEITOR WHERE UPPER(NOME) LIKE %:nome%",  nativeQuery = true)		
public Page<Eleitor> buscarEleitorPeloNome(@Param("nome") String nome, Pageable paginacao);

static String QUERYPAUTA = "SELECT  E.* FROM ELEITOR  E " +
		              "INNER JOIN  PAUTA_ELEITORES T ON E.ID = T.ELEITORES_ID " +
		              "INNER JOIN PAUTA P ON T.PAUTA_ID = P.ID " +
		              "WHERE P.ID = :IdDaPauta";

@Query(value = QUERYPAUTA,  nativeQuery = true)
public Page<Eleitor> buscarEleitorPeloIdDaPauta(@Param("IdDaPauta") Long idDaPauta, Pageable paginacao);


public Page<Eleitor> findById(Long idDoEleitor, Pageable paginacao);

public Page<Eleitor> findByCpf(Long cpf, Pageable paginacao);

public Optional<Eleitor> findByCpf(Long cpf);


static String QUERYELEITORID =  "SELECT ELEITORES_ID  FROM PAUTA_ELEITORES WHERE ELEITORES_ID = :idDoEleitor";
@Query(value = QUERYELEITORID,  nativeQuery = true)
List<Long> buscarIdDoEleitorNasPautas(@Param("idDoEleitor") Long idDoEleitor);





}
