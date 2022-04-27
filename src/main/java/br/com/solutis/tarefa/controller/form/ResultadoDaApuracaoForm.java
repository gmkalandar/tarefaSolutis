package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotNull;

public class ResultadoDaApuracaoForm {
	
	@NotNull
	Long idDaPauta;

	public Long getIdDaPauta() {
		return idDaPauta;
	}

	public void setIdDaPauta(Long idDaPauta) {
		this.idDaPauta = idDaPauta;
	}
	
	

}
