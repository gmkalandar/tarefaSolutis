package br.com.solutis.tarefa.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.solutis.tarefa.modelo.ResultadoDaApuracao;
import br.com.solutis.tarefa.modelo.StatusDaApuracao;

public class ResultadoDaApuracaoDto {

	protected Long id;
	protected Long idDaPauta;
	protected String nomeDaPauta;
	protected LocalDateTime inicioDoPeriodoDeVotacaoDaPauta;
	protected LocalDateTime fimDoPeriodoDeVotacaoDaPauta;
	protected String opcaoVencedora;
	protected Long votosSim;
	protected double porcentagemVotosSim;
	protected Long votosNao;
	protected double porcentagemVotosNao;
	protected Long votosAdulterados = 0L;
	protected boolean validadeDaPauta = true;
	protected boolean validadeDosVotos = true;
	protected LocalDateTime dataDaApuracao;
	protected StatusDaApuracao statusDaApuracao;
	protected Long totaldeVotos;
	protected String mensagem;

	public ResultadoDaApuracaoDto(String mensagem) {
		this.mensagem = mensagem;
	}

	public ResultadoDaApuracaoDto(String nomeDaPauta, String mensagem) {
		this.mensagem = mensagem;
		this.nomeDaPauta = nomeDaPauta;
	}

	public ResultadoDaApuracaoDto(ResultadoDaApuracao resultadoDaApuracao) {

		this.id = resultadoDaApuracao.getId();
		this.opcaoVencedora = resultadoDaApuracao.getOpcaoVencedora();
		this.porcentagemVotosNao = resultadoDaApuracao.getPorcentagemVotosNao();
		this.porcentagemVotosSim = resultadoDaApuracao.getPorcentagemVotosSim();
		this.votosNao = resultadoDaApuracao.getVotosNao();
		this.votosSim = resultadoDaApuracao.getVotosSim();
		this.votosAdulterados = resultadoDaApuracao.getVotosAdulterados();
		this.validadeDaPauta = resultadoDaApuracao.isValidadeDaPauta();
		this.idDaPauta = resultadoDaApuracao.getPauta().getId();
		this.nomeDaPauta = resultadoDaApuracao.getPauta().getNome();
		this.inicioDoPeriodoDeVotacaoDaPauta = resultadoDaApuracao.getPauta().getInicioDoPeriodoDeVotacao();
		this.fimDoPeriodoDeVotacaoDaPauta = resultadoDaApuracao.getPauta().getFimDoPeriodoDeVotacao();
		this.dataDaApuracao = resultadoDaApuracao.getDataDaApuracao();
		this.statusDaApuracao = resultadoDaApuracao.getStatusDaApuracao();
		this.totaldeVotos = resultadoDaApuracao.getTotaldeVotos();
		this.validadeDosVotos = resultadoDaApuracao.isValidadeDosVotos();
		this.mensagem = "Apuração --" + resultadoDaApuracao.getStatusDaApuracao().name() + "-- Concluída";

		if ((!this.isValidadeDaPauta()) || (!this.isValidadeDosVotos()))
			this.mensagem += " -- ALERTA: O processo de auditoria encontrou adulteração no processo de votação!!!";

	}

	public StatusDaApuracao getStatusDaApuracao() {
		return statusDaApuracao;
	}

	public Long getId() {
		return id;
	}

	public String getOpcaoVencedora() {
		return opcaoVencedora;
	}

	public Long getVotosSim() {
		return votosSim;
	}

	public double getPorcentagemVotosSim() {
		return porcentagemVotosSim;
	}

	public Long getVotosNao() {
		return votosNao;
	}

	public double getPorcentagemVotosNao() {
		return porcentagemVotosNao;
	}

	public Long getVotosAdulterados() {
		return votosAdulterados;
	}

	public boolean isValidadeDaPauta() {
		return validadeDaPauta;
	}

	public Long getIdDaPauta() {
		return idDaPauta;
	}

	public String getNomeDaPauta() {
		return nomeDaPauta;
	}

	public LocalDateTime getInicioDoPeriodoDeVotacaoDaPauta() {
		return inicioDoPeriodoDeVotacaoDaPauta;
	}

	public LocalDateTime getFimDoPeriodoDeVotacaoDaPauta() {
		return fimDoPeriodoDeVotacaoDaPauta;
	}

	public LocalDateTime getDataDaApuracao() {
		return dataDaApuracao;
	}

	public Long getTotaldeVotos() {
		return totaldeVotos;
	}

	public String getMensagem() {
		return mensagem;
	}

	public boolean isValidadeDosVotos() {
		return validadeDosVotos;
	}

	public static List<ResultadoDaApuracaoDto> valueOf(List<ResultadoDaApuracao> resultadoDaApuracao) {
		return resultadoDaApuracao.stream().map(ResultadoDaApuracaoDto::new).toList();
	}

}
