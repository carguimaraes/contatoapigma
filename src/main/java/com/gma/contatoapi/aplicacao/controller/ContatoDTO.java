package com.gma.contatoapi.aplicacao.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * 2019-02-21
 * GMA - Carlos A L M Guimaraes
 * 
 */
public class ContatoDTO {

	private Long id;
	
	
	@NotBlank(message="Nome deve ser informado")
	@Size(min=5, message="Nome deve ter no minimo [5] caracteres")
	@Size(max=30,message="Nome muito longo, tamanho maximo [30] caracteres")
	private String nome;
	
	@NotBlank(message="Canal deve ser informado")
	private String canal;
	
	@NotBlank(message="Valor deve ser informado")
	@Size(min=5, message="Valor deve ter no minimo [5] caracteres")
	@Size(max=30,message="Valor muito longo, tamanho maximo [30] caracteres")
	private String valor;
	
	@Size(max=30,message="Observacaor muito longa, tamanho maximo [30] caracteres")
	private String obs;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCanal() {
		return this.canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
