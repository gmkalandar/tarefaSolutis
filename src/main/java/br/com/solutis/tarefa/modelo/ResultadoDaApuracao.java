package br.com.solutis.tarefa.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.ToString;

@ToString
@Entity
public class ResultadoDaApuracao implements Comparable<ResultadoDaApuracao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "pauta_id")
	protected Pauta pauta;

	protected String opcaoVencedora;
	protected Long votosSim;
	protected double porcentagemVotosSim;
	protected Long votosNao;
	protected double porcentagemVotosNao;
	protected Long votosAdulterados;
	protected boolean validadeDaPauta;
	protected boolean validadeDosVotos;
	protected LocalDateTime dataDaApuracao;
	protected Long totaldeVotos;

	@Enumerated(EnumType.STRING)
	protected StatusDaApuracao statusDaApuracao;

	public ResultadoDaApuracao() {
		// Construtor default
		super();
	}

	public StatusDaApuracao getStatusDaApuracao() {
		return statusDaApuracao;
	}

	public void setStatusDaApuracao(StatusDaApuracao statusDaApuracao) {
		this.statusDaApuracao = statusDaApuracao;
	}

	public LocalDateTime getDataDaApuracao() {
		return dataDaApuracao;
	}

	public void setDataDaApuracao(LocalDateTime dataDaApuracao) {
		this.dataDaApuracao = dataDaApuracao;
	}

	public Long getId() {
		return id;
	}

	public String getOpcaoVencedora() {
		return opcaoVencedora;
	}

	public void setOpcaoVencedora(String opcaoVencedora) {
		this.opcaoVencedora = opcaoVencedora;
	}

	public Long getVotosSim() {
		return votosSim;
	}

	public void setVotosSim(Long votosSim) {
		this.votosSim = votosSim;
	}

	public double getPorcentagemVotosSim() {
		return porcentagemVotosSim;
	}

	public void setPorcentagemVotosSim(double porcentagemVotosSim) {
		this.porcentagemVotosSim = porcentagemVotosSim;
	}

	public Long getVotosNao() {
		return votosNao;
	}

	public void setVotosNao(Long votosNao) {
		this.votosNao = votosNao;
	}

	public double getPorcentagemVotosNao() {
		return porcentagemVotosNao;
	}

	public void setPorcentagemVotosNao(double porcentagemVotosNao) {
		this.porcentagemVotosNao = porcentagemVotosNao;
	}

	public Long getVotosAdulterados() {
		return votosAdulterados;
	}

	public void setVotosAdulterados(Long votosAdulterados) {
		this.votosAdulterados = votosAdulterados;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Long getTotaldeVotos() {
		return totaldeVotos;
	}

	public void setTotaldeVotos(Long totaldeVotos) {
		this.totaldeVotos = totaldeVotos;
	}

	public boolean isValidadeDosVotos() {
		return validadeDosVotos;
	}

	public void setValidadeDosVotos(boolean validadeDosVotos) {
		this.validadeDosVotos = validadeDosVotos;
	}

	public boolean isValidadeDaPauta() {
		return validadeDaPauta;
	}

	public void setValidadeDaPauta(boolean validadeDaPauta) {
		this.validadeDaPauta = validadeDaPauta;
	}

	
	
	
	@Override
	public boolean equals(Object obj) {
	     return  super.equals(obj);
	          
	}
	
	@Override
	public int compareTo(ResultadoDaApuracao resuldadoDaApuracao) {
		if (getDataDaApuracao() == null || resuldadoDaApuracao.getDataDaApuracao() == null) {
			return 0;
		}
		return getDataDaApuracao().compareTo(resuldadoDaApuracao.getDataDaApuracao());
	}

}
