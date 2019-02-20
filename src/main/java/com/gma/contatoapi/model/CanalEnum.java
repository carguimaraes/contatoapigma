package com.gma.contatoapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CanalEnum {
	
	//Email("EMAIL"),Celular("CELULAR"),Fixo("FIXO");
	Email,
	Celular, 
	Fixo;
	
	/*
	private String _canal;
   

	CanalEnum(String canal) {
		this._canal=canal;
	}
	

	public String getValor() {
		return this._canal;
	}
	
	@Override
	public String toString() {
		return this._canal;
	}
	*/
	 
}
