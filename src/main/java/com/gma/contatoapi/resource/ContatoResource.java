package com.gma.contatoapi.resource;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gma.contatoapi.model.CanalEnum;
import com.gma.contatoapi.model.Contato;
import com.gma.contatoapi.repository.ContatoRepository;


@RestController
@RequestMapping("api/v1")
public class ContatoResource {
	
	private static final Logger log = LoggerFactory.getLogger(ContatoResource.class);
	
	@Autowired
	private ContatoRepository _contatoRepository;
	
	
	@RequestMapping(value = "/contatos/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object>  buscar( @PathVariable("id") Long id){
		log.info("=======================GMA---> Buscar  id:"+id);
	

		Optional<Contato> contato=  this._contatoRepository.findById(id);
		
		 
		if(contato.isPresent()) {
			return ResponseEntity.ok().body(contato.get());
		}
		else
		{
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new String[] {"Informacao nao encontrada"});
			   
			
		}
		
	}

	
	//{{baseUrl}}/?size=5&page=1&sort=nome

	@RequestMapping(value = "/contatos", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object>  listarPag(Pageable pag){
		log.info("=======================GMA---> lista recurso");
	
		Page<Contato> lstContato=  this._contatoRepository.findAll(pag );
		
		
		return   ResponseEntity.ok().body(lstContato);
	}
	
	
	
	
    @RequestMapping(value = "/contatos", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<Contato>  novo(){
	
		Contato contato = new Contato();
		contato.setNome("Carlos Contato 101");
		contato.setCanal(CanalEnum.Email);
		
		contato=_contatoRepository.save(contato);
		
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(contato.getId())
                 .toUri();
		
		return   ResponseEntity.created(location).build();
	}
	
}
