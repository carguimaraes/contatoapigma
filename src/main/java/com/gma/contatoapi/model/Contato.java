package com.gma.contatoapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="CONTATO_TB")
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private CanalEnum canal;
	private String valor;
	private String obs;
	
	public Contato() {}

	public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public CanalEnum getCanal() {
		return this.canal;
	}
	public void setCanal(CanalEnum canal) {
		this.canal = canal;
	}

	
	public String getValor() {
		return this.valor;
	}
	
	public void setValor(String valor) {
		this.valor=valor;
	}
	
	public String getObs() {
		return this.obs;
	}
	
	public void setObs(String obs) {
		this.obs=obs;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
