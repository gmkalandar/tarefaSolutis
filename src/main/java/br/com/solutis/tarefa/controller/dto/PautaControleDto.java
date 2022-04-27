package br.com.solutis.tarefa.controller.dto;

import java.time.LocalDateTime;

import br.com.solutis.tarefa.modelo.Pauta;

public class PautaControleDto {

	private Long idDaPauta;
	private String nome;
	private LocalDateTime inicioDoPeriodoDeVotacao;
	private LocalDateTime fimDoPeriodoDeVotacao;
	private boolean statusControle;
	private String mensagem;
	

	public PautaControleDto() {
	}

	public PautaControleDto(boolean statusControle, String mensagem) {

		this.statusControle = statusControle;
		this.mensagem = mensagem;

	}

	public PautaControleDto(Long idDaPauta, boolean statusControle, String nome, String mensagem) {

		this.idDaPauta = idDaPauta;
		this.nome = nome;
		this.statusControle = statusControle;
		this.mensagem = mensagem;

	}
			

	public PautaControleDto(Long idDaPauta, boolean statusControle,  String nome, String mensagem,
			LocalDateTime inicioDoPeriodoDeVotacao, LocalDateTime fimDoPeriodoDeVotacao) {
		
		this.idDaPauta = idDaPauta;
		this.nome = nome;
		this.statusControle = statusControle;
		this.mensagem = mensagem;
		this.inicioDoPeriodoDeVotacao = inicioDoPeriodoDeVotacao;
		this.fimDoPeriodoDeVotacao = fimDoPeriodoDeVotacao;
		
	}
	
	public PautaControleDto(boolean statusControle, String mensagem, Pauta pauta) {
		
		this.idDaPauta = pauta.getId();
		this.nome = pauta.getNome();
		this.statusControle = statusControle;
		this.mensagem = mensagem;
		this.inicioDoPeriodoDeVotacao = pauta.getInicioDoPeriodoDeVotacao();
		this.fimDoPeriodoDeVotacao = pauta.getFimDoPeriodoDeVotacao();
		
	}

	public String getNome() {
		return nome;
	}

	public LocalDateTime getInicioDoPeriodoDeVotacao() {
		return inicioDoPeriodoDeVotacao;
	}

	public LocalDateTime getFimDoPeriodoDeVotacao() {
		return fimDoPeriodoDeVotacao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setInicioDoPeriodoDeVotacao(LocalDateTime inicioDoPeriodoDeVotacao) {
		this.inicioDoPeriodoDeVotacao = inicioDoPeriodoDeVotacao;
	}

	public void setFimDoPeriodoDeVotacao(LocalDateTime fimDoPeriodoDeVotacao) {
		this.fimDoPeriodoDeVotacao = fimDoPeriodoDeVotacao;
	}

	public Long getIdDaPauta() {
		return idDaPauta;
	}

	public boolean isStatusControle() {
		return statusControle;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setIdDaPauta(Long idDaPauta) {
		this.idDaPauta = idDaPauta;
	}

	public void setStatusControle(boolean statusControle) {
		this.statusControle = statusControle;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
