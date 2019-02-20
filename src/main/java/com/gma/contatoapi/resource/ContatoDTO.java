package com.gma.contatoapi.resource;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.gma.contatoapi.model.CanalEnum;

public class ContatoDTO {
	public Long id;
	public String nome;
	public CanalEnum canal;
	public ContatoDTO(Long id, String nome, CanalEnum canal) {
		 
		this.id = id;
		this.nome = nome;
		this.canal = canal;
	}
	 
	
 

}
