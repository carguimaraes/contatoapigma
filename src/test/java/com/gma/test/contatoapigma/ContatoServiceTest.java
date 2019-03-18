package com.gma.test.contatoapigma;


 

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gma.contatoapi.aplicacao.service.ContatoService;
import com.gma.contatoapi.aplicacao.service.IContatoService;
import com.gma.contatoapi.model.entidade.CanalEnum;
import com.gma.contatoapi.model.entidade.Contato;
import com.gma.contatoapi.model.repositorio.ContatoRepository;




@RunWith(MockitoJUnitRunner.class)
public class ContatoServiceTest {

	
	private  IContatoService _contatoService;
	
	@Mock
	private  ContatoRepository _contatoRepository;
	
	@Before
    public void setup() {
     
		_contatoService=  ContatoService.New(_contatoRepository);
    }
	
	
	@Test
	public void buscar_falha_informacaoNaoEncontrada() 
	{
		
		//ArgumentCaptor<TransacaoMensagem> msgCap=ArgumentCaptor.forClass(TransacaoMensagem.class);
		
		Optional<Contato> optContato= Optional.empty();
		
		
		Mockito.
		when(_contatoRepository.findById(1L))
			.thenReturn(optContato);
		
		
		 ResponseEntity<Object> resp=_contatoService.buscar(1L);
		 
		 
		 String[] msg= (String[]) resp.getBody();
		 
		 HttpStatus codRet=    resp.getStatusCode();
		 
		 
		 Assert.assertTrue( codRet.equals(HttpStatus.NOT_FOUND));
		 Assert.assertTrue( msg[0].equals(IContatoService.INF_N_ENC));
		 
		 
		 
		
	}
	
	@Test
	public void buscar_sucesso_retornaContato() 
	{
		
		//ArgumentCaptor<ContatoRepository> msgCap=ArgumentCaptor.forClass(ContatoRepository.class);
		
		//Optional<Contato> optContato= Optional.empty();
		Contato contato = new Contato();
		contato.setCanal(CanalEnum.Celular);
		contato.setNome("Carlos");
		contato.setObs("teste GMA");
		contato.setValor("9991111");
		
		Optional<Contato> optContato= Optional.of(contato);
	
		
		
		Mockito.
		when(_contatoRepository.findById(1967L))
			.thenReturn(optContato);
		
		
		 ResponseEntity<Object> resp=_contatoService.buscar(1967L);
		 
		 
		 Contato retContato= (Contato) resp.getBody();
		 
		 HttpStatus codRet=    resp.getStatusCode();
		 
		 System.out.println("===>"+retContato.getNome());
		 
		 Assert.assertTrue( codRet.equals(HttpStatus.OK));
		 Assert.assertTrue( retContato.getNome().equals("Carlos"));
		 
		 
		 
		
	}

	
}
