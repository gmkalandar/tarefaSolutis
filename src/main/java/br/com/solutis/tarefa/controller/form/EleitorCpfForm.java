package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotNull;

public class EleitorCpfForm {

	@NotNull
	protected Long cpf;

	public EleitorCpfForm() {}
	
	public EleitorCpfForm(Long cpf) {

		this.cpf = cpf;

	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
