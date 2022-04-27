package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EleitorCadastroForm {
	
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	protected String nome;
	
	@NotNull
	protected Long cpf;
	
	public EleitorCadastroForm() {}
	
	public EleitorCadastroForm(Long id, String nome, Long cpf) {
		
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
