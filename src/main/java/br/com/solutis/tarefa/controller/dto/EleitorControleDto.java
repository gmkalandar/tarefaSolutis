package br.com.solutis.tarefa.controller.dto;

import br.com.solutis.tarefa.modelo.Eleitor;

public class EleitorControleDto {

	protected Long id;
	protected String nome;
	protected Long cpf;
	private boolean statusControle;
	private String mensagem;

	public EleitorControleDto() {
	}

	public EleitorControleDto(boolean statusControle, String mensagem, Eleitor eleitor) {

		this.id = eleitor.getId();
		this.nome = eleitor.getNome();
		this.cpf = eleitor.getCpf();
		this.mensagem = mensagem;
		this.statusControle = statusControle;

	}

	public EleitorControleDto(boolean statusControle, String mensagem) {

		this.mensagem = mensagem;
		this.statusControle = statusControle;

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public boolean isStatusControle() {
		return statusControle;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setStatusControle(boolean statusControle) {
		this.statusControle = statusControle;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	

}
