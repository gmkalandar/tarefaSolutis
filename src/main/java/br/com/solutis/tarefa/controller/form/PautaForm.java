package br.com.solutis.tarefa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PautaForm {
    

	protected Long id;

	@NotNull
	@NotEmpty
	@NotBlank
	protected String nome;

	protected int duracao;

	public PautaForm() {
	}

	public PautaForm(String nome, int duracao) {

		this.nome = nome;
		this.duracao = duracao;

	}

	public String getNome() {
		return nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		
			this.id = id;
		
	}

}
