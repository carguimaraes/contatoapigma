package com.gma.contatoapi.aplicacao.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gma.contatoapi.model.entidade.CanalEnum;
import com.gma.contatoapi.model.entidade.Contato;
import com.gma.contatoapi.model.repositorio.ContatoRepository;

/*
 * 2019-02-21
 * GMA - Carlos A L M Guimaraes
 * 
 */

/*
 * Atencao: O codigo deste controller pode ser fatorado, retirar logicas de validacao e colocar em servicos
 * 
 */

//http://localhost:8089/api/v1/contatos
@RestController
@RequestMapping("api/v1")
public class ContatoController {

	private static final Logger log = LoggerFactory.getLogger(ContatoController.class);

	@Autowired
	private ContatoRepository _contatoRepository;

	@RequestMapping(value = "/contatos/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> buscar(@PathVariable("id") Long id) {
		
		log.info("=======================GMA---> Buscar  id:" + id);

		Optional<Contato> optContato = this._contatoRepository.findById(id);
		
		if (!optContato.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] { "Informacao nao encontrada" });
		}

	
		//TODO retornar ContatoDTO
		
		return ResponseEntity.ok().body(optContato.get());
	
	}

	@RequestMapping(value = "/contatos/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> alterar(@PathVariable("id") Long id, @RequestBody ContatoDTO contatoDTO) {

	
		Optional<Contato> optContato = this._contatoRepository.findById(id);

		if (!optContato.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] { "Informacao nao encontrada" });
		}

		log.info("=======================GMA---> Altera:" + contatoDTO.getNome() + " - " + contatoDTO.getCanal());

		List<String> lst=_getMsgErro(contatoDTO);		
		
		if(!lst.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(lst);
		}
		
		
		try {
			CanalEnum canal = CanalEnum.valueOf(contatoDTO.getCanal());
			Contato contato = optContato.get();
			contato.setNome(contatoDTO.getNome());
			contato.setCanal(canal);
			contato.setValor(contatoDTO.getValor());
			contato.setObs(contatoDTO.getObs());
			this._contatoRepository.save(contato);
			
			return ResponseEntity.noContent().build();

		} catch (IllegalArgumentException ex) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new String[] { "Tipo de canal invalido. Usar: [Email, Fixp, Celular]" });
		}

	}

	@RequestMapping(value = "/contatos/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> deletar(@PathVariable("id") Long id) {

		Optional<Contato> optContato = this._contatoRepository.findById(id);
		
		if (!optContato.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] { "Informacao nao encontrada" });
		}

		
		this._contatoRepository.delete(optContato.get());

		return ResponseEntity.noContent().build();
		

	}

	// {{baseUrl}}/?size=5&page=1&sort=nome
	@RequestMapping(value = "/contatos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> paginar(@PageableDefault(page = 0, size = 10, sort = "nome") Pageable pag) {
		log.info("=======================GMA---> lista recurso");

		// TODO nao estava na especificacao, mas achei melhor colocar para poder
		// controlar o tamanho
		if (pag.getPageSize() > 15) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new String[] { "Nao é permitido tamanho de pagina superior a 15" });

		}

		Page<Contato> pageContato = this._contatoRepository.findAll(pag);

		// TODO c 401 (Unauthorized) // 404 (Not Found)

		log.info("=======================GMA---> Has:" + pageContato.hasContent());

		if (pageContato.hasContent()) {
			return ResponseEntity.ok().body(pageContato);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pageContato);
		}

	}

	@RequestMapping(value = "/contatos", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	//public ResponseEntity<Object> novo(@Valid @RequestBody ContatoDTO contatoDTO) 
	//Optei por nao usar o @Valid para nao ter que ajustar mensagem de retorno
	public ResponseEntity<Object> novo(@RequestBody ContatoDTO contatoDTO) {
		
		List<String> lst=_getMsgErro(contatoDTO);		
		
		if(!lst.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(lst);
		}

		try {
			Contato contato= new Contato();
			CanalEnum canal = CanalEnum.valueOf(contatoDTO.getCanal());
			contato.setNome(contatoDTO.getNome());
			contato.setCanal(canal);
			contato.setValor(contatoDTO.getValor());
			contato.setObs(contatoDTO.getObs());
			contato=this._contatoRepository.save(contato);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		} catch (IllegalArgumentException ex) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new String[] { "Tipo de canal invalido. Usar: [Email, Fixp, Celular]" });
		}
		
		
				
	}
	
	private List<String> _getMsgErro(ContatoDTO contatoDTO) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<ContatoDTO>> violations = validator.validate(contatoDTO);

	//	List<String> lst=new ArrayList<String>();
	//	violations.forEach( (item)->{ lst.add( item.getMessage());});
	
		List<String> lst= violations
							.stream()
							.map( item->  item.getMessage())
							.collect(Collectors.toList());
		
		 
		return lst;
	}

}
