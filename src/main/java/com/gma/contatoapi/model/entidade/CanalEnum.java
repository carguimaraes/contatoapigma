package com.gma.contatoapi.model.entidade;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/*
 * 2019-02-21
 * GMA - Carlos A L M Guimaraes
 * 
 */
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
