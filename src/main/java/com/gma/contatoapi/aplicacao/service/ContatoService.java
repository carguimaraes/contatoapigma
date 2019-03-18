package com.gma.contatoapi.aplicacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gma.contatoapi.model.entidade.Contato;
import com.gma.contatoapi.model.repositorio.ContatoRepository;

public class ContatoService implements IContatoService {
	
	 
	private ContatoRepository _contatoRepository;
	
	
	
	
	@Autowired
	private ContatoService(ContatoRepository contatoRepository) {
		
		_contatoRepository=contatoRepository;
	}
	
	public static IContatoService New(ContatoRepository contatoRepository)
	{
		return new  ContatoService(contatoRepository);
	}
	

	@Override
	public ResponseEntity<Object> buscar(Long id) {
		
		Optional<Contato> optContato = this._contatoRepository.findById(id);
		
		if (!optContato.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] { "Informacao nao encontrada" });
		}

				
		return ResponseEntity.ok().body(optContato.get());
	}

}
