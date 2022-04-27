package br.com.solutis.tarefa.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.solutis.tarefa.modelo.CedulaEletronica;
import br.com.solutis.tarefa.modelo.ValorDoVoto;

public class CedulaEletronicaDto {

	protected Long id;
	protected Long pautaId;
	protected String nomeDaPauta;
	protected LocalDateTime dataDoVoto;
	protected ValorDoVoto valorDoVoto;
	protected String hashVoto;

	
	public CedulaEletronicaDto(CedulaEletronica cedulaEletronica) {
		
		this.id = cedulaEletronica.getId();
		this.dataDoVoto = cedulaEletronica.getDataDoVoto();
		this.hashVoto = cedulaEletronica.getHashVoto();
		this.pautaId = cedulaEletronica.getPauta().getId();
		this.nomeDaPauta = cedulaEletronica.getPauta().getNome();
		this.valorDoVoto = cedulaEletronica.isValorDoVoto();
		
	}
	
	
	public Long getId() {
		return id;
	}


	
	public LocalDateTime getDataDoVoto() {
		return dataDoVoto;
	}


	public ValorDoVoto getValorDoVoto() {
		return valorDoVoto;
	}


	public String getHashVoto() {
		return hashVoto;
	}

	public Long getPautaId() {
		return pautaId;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setPautaId(Long pautaId) {
		this.pautaId = pautaId;
	}


	public void setNomeDaPauta(String nomeDaPauta) {
		this.nomeDaPauta = nomeDaPauta;
	}


	
	public void setDataDoVoto(LocalDateTime dataDoVoto) {
		this.dataDoVoto = dataDoVoto;
	}


	public void setValorDoVoto(ValorDoVoto valorDoVoto) {
		this.valorDoVoto = valorDoVoto;
	}


	public void setHashVoto(String hashVoto) {
		this.hashVoto = hashVoto;
	}


	
	public String getNomeDaPauta() {
		return nomeDaPauta;
	}

	

	public static List<CedulaEletronicaDto> valueOf(List<CedulaEletronica> cedulaEletronica){
		return cedulaEletronica.stream().map(CedulaEletronicaDto::new).toList();
	}
	
}
