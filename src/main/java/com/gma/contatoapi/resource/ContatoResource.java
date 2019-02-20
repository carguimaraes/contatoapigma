package com.gma.contatoapi.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	

	@RequestMapping(value = "/contatos", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<Object>  listar(){
		log.info("=======================GMA---> lista recurso");
	

		List<Contato> lstContato=  this._contatoRepository.findAll();
		
		 
		
		List<ContatoDTO> lstContatoDTO=new ArrayList<>();
		
//		for(Contato item: lstContato) {
//			lstContatoDTO.add(new ContatoDTO(item.getId(),item.getNome(),item.getCanal().getCanal()));
//		}
		
		lstContatoDTO.add(new ContatoDTO(1L,"Teste 1",CanalEnum.Email));
		lstContatoDTO.add(new ContatoDTO(2L,"Teste 2",CanalEnum.Celular));
		lstContatoDTO.add(new ContatoDTO(3L,"Teste 3",CanalEnum.Fixo));
		lstContatoDTO.add(new ContatoDTO(3L,"Teste 4",CanalEnum.Fixo));
		
		return   ResponseEntity.ok().body(lstContato);
	}
	
	

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
