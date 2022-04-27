package br.com.solutis.tarefa.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class CedulaEletronica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToOne
	@JoinColumn(name = "pauta_id",  nullable = false)
	protected Pauta pauta;

	protected LocalDateTime dataDoVoto;

	@Enumerated(EnumType.STRING)
	protected ValorDoVoto valorDoVoto;

	@Column(unique = true)
	protected String hashVoto;

	

	public CedulaEletronica() {
	}

	public CedulaEletronica(Long id, Pauta pauta, LocalDateTime dataDoVoto, ValorDoVoto valorDoVoto) {

		this.id = id;
		this.pauta = pauta;
		this.dataDoVoto = dataDoVoto;
		this.valorDoVoto = valorDoVoto;

	}

	public CedulaEletronica(Pauta pauta, LocalDateTime dataDoVoto, ValorDoVoto valorDoVoto) {

		this.pauta = pauta;
		this.dataDoVoto = dataDoVoto;
		this.valorDoVoto = valorDoVoto;

	}

	public CedulaEletronica(Long id, Pauta pauta, LocalDateTime dataDoVoto, ValorDoVoto valorDoVoto, String hashVoto) {

		this.id = id;
		this.pauta = pauta;
		this.dataDoVoto = dataDoVoto;
		this.valorDoVoto = valorDoVoto;
		this.hashVoto = hashVoto;

	}

	public CedulaEletronica(Pauta pauta, LocalDateTime dataDoVoto, ValorDoVoto valorDoVoto, String hashVoto) {

		this.pauta = pauta;
		this.dataDoVoto = dataDoVoto;
		this.valorDoVoto = valorDoVoto;
		this.hashVoto = hashVoto;

	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public LocalDateTime getDataDoVoto() {
		return dataDoVoto;
	}

	public void setDataDoVoto(LocalDateTime dataDoVoto) {
		this.dataDoVoto = dataDoVoto;
	}

	public ValorDoVoto isValorDoVoto() {
		return valorDoVoto;
	}

	public void setValorDoVoto(ValorDoVoto valorDoVoto) {
		this.valorDoVoto = valorDoVoto;
	}

	public Long getId() {
		return id;
	}

	public String getHashVoto() {
		return hashVoto;
	}

	public void setHashVoto(String hashVoto) {
		this.hashVoto = hashVoto;
	}
}
