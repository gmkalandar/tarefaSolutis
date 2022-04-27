package br.com.solutis.tarefa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.solutis.tarefa.controller.form.EleitorCadastroForm;
import lombok.ToString;

@ToString
@Entity
public class Eleitor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String nome;
	
	
	protected Long cpf;
	
	
	

	public Eleitor() {
	}

	public Eleitor(Long id, String nome, Long cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void atualizar(EleitorCadastroForm eleitorCadastroForm) {
		
		this.id = eleitorCadastroForm.getId();
		this.nome = eleitorCadastroForm.getNome();
		this.cpf = eleitorCadastroForm.getCpf();
	}
}
