package br.com.solutis.tarefa.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.solutis.tarefa.modelo.Pauta;

public class PautaDto {

	protected Long id;
	protected String nome;
	protected int duracao = 1;
	private LocalDateTime inicioDoPeriodoDeVotacao;
	private LocalDateTime fimDoPeriodoDeVotacao;
	protected boolean pleitoAnulado = false;
	
	
	
	public PautaDto() {}
	
	
	public PautaDto(Pauta pauta) {
		
		this.id = pauta.getId();
		this.nome = pauta.getNome();
		this.duracao = pauta.getDuracao();
		this.inicioDoPeriodoDeVotacao = pauta.getInicioDoPeriodoDeVotacao();
		this.fimDoPeriodoDeVotacao = pauta.getFimDoPeriodoDeVotacao();
		this.pleitoAnulado = pauta.isPleitoAnulado();
		
	}
	
	

	public Long getId() {
		return id;
	}

	

	public String getNome() {
		return nome;
	}

	

	public int getDuracao() {
		return duracao;
	}

	

	public LocalDateTime getInicioDoPeriodoDeVotacao() {
		return inicioDoPeriodoDeVotacao;
	}

	

	public LocalDateTime getFimDoPeriodoDeVotacao() {
		return fimDoPeriodoDeVotacao;
	}

		
	public boolean isPleitoAnulado() {
		return pleitoAnulado;
	}



	public static List<PautaDto> valueOf(List<Pauta> pauta) {
		return pauta.stream().map(PautaDto::new).toList();
	}
	
}
