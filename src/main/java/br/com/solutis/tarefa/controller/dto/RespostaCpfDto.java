package br.com.solutis.tarefa.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaCpfDto {

	private String cpf;
	private boolean validade;
	
	
	public RespostaCpfDto() {}
	
	public RespostaCpfDto(String cpf, boolean validade){
		this.cpf = cpf;
		this.validade = validade;
	}
	
	@JsonProperty(value="cpf")
	public String getCpf() {
		return cpf;
	}

	@JsonProperty(value="cpf")
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@JsonProperty(value="isValid")
	public boolean isValidade() {
		return validade;
	}
	@JsonProperty(value="isValid")
	public void setValidade(boolean validade) {
		this.validade = validade;
	}
	
	
	
}
