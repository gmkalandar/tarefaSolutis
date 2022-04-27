package br.com.solutis.tarefa.controller.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.modelo.Eleitor;
import br.com.solutis.tarefa.modelo.Pauta;
import br.com.solutis.tarefa.modelo.ValorDoVoto;
import br.com.solutis.tarefa.repository.EleitorRepository;
import br.com.solutis.tarefa.repository.PautaRepository;

public class CedulaEletronicaForm {

	@NotNull
	private Long idDaPauta;
	
	@NotNull
	private Long idDoEleitor;
	
	@NotNull
	private ValorDoVoto valorDoVoto;
	
	
	public Long getIdDoEleitor() {
		return idDoEleitor;
	}
	public void setIdDoEleitor(Long idDoEleitor) {
		this.idDoEleitor = idDoEleitor;
	}
	
	public Long getIdDaPauta() {
		return idDaPauta;
	}
	public ValorDoVoto getValorDoVoto() {
		return valorDoVoto;
	}
	
	public void setIdDaPauta(Long idDaPauta) {
		this.idDaPauta = idDaPauta;
	}
	public void setValorDoVoto(ValorDoVoto valorDoVoto) {
		this.valorDoVoto = valorDoVoto;
	}
	
	public CedulaEletronica valueOf(PautaRepository pautaRepository ) {
		
		Optional<Pauta> pauta = pautaRepository.findById(idDaPauta);
		Pauta pautaSaida = new Pauta();
		if(pauta.isPresent()) pautaSaida = pauta.get(); 
		return new CedulaEletronica(pautaSaida, LocalDateTime.now(), valorDoVoto);
	
	}
	
	public Eleitor valueOf(EleitorRepository eleitorRepository ) {
		Optional<Eleitor> eleitor = eleitorRepository.findById(idDoEleitor) ; 
		Eleitor eleitorSaida = new Eleitor();
		if(eleitor.isPresent()) eleitorSaida = eleitor.get(); 
		return eleitorSaida;
	}
}
