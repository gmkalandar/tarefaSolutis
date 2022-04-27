package br.com.solutis.tarefa.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.com.solutis.tarefa.controller.form.PautaForm;
import lombok.ToString;

@ToString
@Entity
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Eleitor> eleitores = new ArrayList<>();

	@OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<CedulaEletronica> votos = new ArrayList<>();

	@OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<ResultadoDaApuracao> resultadosDaApuracao = new ArrayList<>();
   
	protected int duracao = 1;
	
	protected boolean pleitoAnulado = false;

	protected LocalDateTime inicioDoPeriodoDeVotacao;
	protected LocalDateTime fimDoPeriodoDeVotacao;
	protected String hashEleicao;

	public Pauta() {
	}

	public Pauta(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Pauta(Long id, String nome, int duracao) {
		this.id = id;
		this.nome = nome;
		this.duracao = duracao;
	}
	
	public Pauta(String nome, int duracao) {
	
		this.nome = nome;
		this.duracao = duracao;
	}

	public String getHashEleicao() {
		return hashEleicao;
	}

	public void setHashEleicao(String hashEleicao) {
		this.hashEleicao = hashEleicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInicioDoPeriodoDeVotacao() {
		return inicioDoPeriodoDeVotacao;
	}

	public void setInicioDoPeriodoDeVotacao(LocalDateTime inicioDoPeriodoDeVotacao) {
		this.inicioDoPeriodoDeVotacao = inicioDoPeriodoDeVotacao;
	}

	public LocalDateTime getFimDoPeriodoDeVotacao() {
		return fimDoPeriodoDeVotacao;
	}

	public void setFimDoPeriodoDeVotacao(LocalDateTime fimDoPeriodoDeVotacao) {
		this.fimDoPeriodoDeVotacao = fimDoPeriodoDeVotacao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		if (duracao < 0)
			duracao = 1;
		this.duracao = duracao;
	}

	public List<Eleitor> getEleitores() {
		return eleitores;
	}

	public void setEleitores(List<Eleitor> eleitores) {
		this.eleitores = eleitores;
	}

	public List<CedulaEletronica> getVotos() {
		return votos;
	}

	public void setVotos(List<CedulaEletronica> votos) {
		this.votos = votos;
	}

	public List<ResultadoDaApuracao> getResultadosDaApuracao() {
		return resultadosDaApuracao;
	}

	public void setResultadosDaApuracao(List<ResultadoDaApuracao> resultadosDaApuracao) {
		this.resultadosDaApuracao = resultadosDaApuracao;
	}

	public boolean isPleitoAnulado() {
		return pleitoAnulado;
	}

	public void setPleitoAnulado(boolean pleitoAnulado) {
		this.pleitoAnulado = pleitoAnulado;
	}

	public boolean isVotacaoAberta() {
		return this.fimDoPeriodoDeVotacao != null;
	}

	public boolean isVotacaoEncerrada() {
		return this.fimDoPeriodoDeVotacao != null && this.fimDoPeriodoDeVotacao.isBefore(LocalDateTime.now());
	}

	public boolean abrirVotacao() {
		
		if (!isVotacaoAberta()) {

			this.inicioDoPeriodoDeVotacao = LocalDateTime.now();
			this.fimDoPeriodoDeVotacao = LocalDateTime.now().plusMinutes(this.duracao);
			return true;
		}
		
		return false;
	}
	
	public boolean  fecharVotacao() {
		
		if (isVotacaoAberta()) {
			
			this.fimDoPeriodoDeVotacao = LocalDateTime.now();
			return true;
		}
		
		return false;
	}

	public void atualizar(PautaForm pautaForm) {
		
		this.setNome(pautaForm.getNome());
		this.setDuracao(pautaForm.getDuracao());
		
	}
	
	

}
