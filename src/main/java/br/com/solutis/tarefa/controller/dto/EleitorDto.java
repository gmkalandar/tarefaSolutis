package br.com.solutis.tarefa.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.solutis.tarefa.modelo.Eleitor;

public class EleitorDto {
	
	protected Long id;
	protected String nome;
	protected Long cpf;

	public EleitorDto(Eleitor eleitor) {
		
		this.id = eleitor.getId();
		this.nome = eleitor.getNome();
		this.cpf = eleitor.getCpf();
		
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


	public static List<EleitorDto> valueOf(List<Eleitor> eleitor) {
		return eleitor.stream().map(EleitorDto::new).toList();	
	}
	
	public static Page<EleitorDto> valueOf(Page<Eleitor> eleitor) {
		return eleitor.map(EleitorDto::new);	
	}
	
}
