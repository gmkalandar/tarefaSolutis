package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EleitorForm {
	
	@NotNull
	@NotEmpty
	@NotBlank
	protected String nome;
	
	@NotNull
	protected Long cpf;
	
	public EleitorForm(String nome, Long cpf) {
		
		this.nome = nome;
		this.cpf = cpf;

	}

	public String getNome() {
		return nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	
	
	
}
