package br.com.solutis.tarefa.controller.dto;

import java.time.LocalDateTime;

import br.com.solutis.tarefa.modelo.CedulaEletronica;

public class ConfirmacaoVotoDto {


	protected LocalDateTime dataDoVoto;
	protected String hashVoto;
	protected String nomeDaPauta;
	protected String mensagem = "";

	

	public ConfirmacaoVotoDto() {}
	
	
	public ConfirmacaoVotoDto(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public ConfirmacaoVotoDto(String nomeDaPauta, String mensagem) {
		this.nomeDaPauta = nomeDaPauta;
		this.mensagem = mensagem;
	}
	
	public ConfirmacaoVotoDto(CedulaEletronica cedulaEletronica) {
		
		this.hashVoto = cedulaEletronica.getHashVoto();
		this.dataDoVoto = cedulaEletronica.getDataDoVoto();
		this.nomeDaPauta = cedulaEletronica.getPauta().getNome();

	}

	public String getHashVoto() {
		return hashVoto;
	}

	public void setHashVoto(String hashVoto) {
		this.hashVoto = hashVoto;
	}

	public LocalDateTime getDataDoVoto() {
		return dataDoVoto;
	}

	public void setDataDoVoto(LocalDateTime dataDoVoto) {
		this.dataDoVoto = dataDoVoto;
	}

	public String getNomeDaPauta() {
		return nomeDaPauta;
	}

	public void setNomeDaPauta(String nomeDaPauta) {
		this.nomeDaPauta = nomeDaPauta;
	}


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
