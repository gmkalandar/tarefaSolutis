package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PautaNomeForm {

	@NotNull
	@NotEmpty
	@NotBlank
	protected String nome;

	public PautaNomeForm() {
	}

	public PautaNomeForm(String nome) {

		this.nome = nome;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
