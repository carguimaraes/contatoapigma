package com.gma.contatoapi.aplicacao.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IContatoService {
	
	
	final String INF_N_ENC="Informacao nao encontrada"; 
	
	ResponseEntity<Object> buscar(@PathVariable("id") Long id);

}
