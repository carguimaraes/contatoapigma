package com.gma.test.contatoapigma;


 

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.gma.contatoapi.aplicacao.service.ContatoService;
import com.gma.contatoapi.aplicacao.service.IContatoService;
import com.gma.contatoapi.model.entidade.CanalEnum;
import com.gma.contatoapi.model.entidade.Contato;
import com.gma.contatoapi.model.repositorio.ContatoRepository;

import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.client.ClientProtocolException;
import wiremock.org.apache.http.client.methods.HttpGet;
import wiremock.org.apache.http.impl.client.CloseableHttpClient;
import wiremock.org.apache.http.impl.client.HttpClients;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(MockitoJUnitRunner.class)
public class ContatoServiceTest {

	
	private static WireMockServer _wireMockServer;
	private  IContatoService _contatoService;
	
	@Mock
	private  ContatoRepository _contatoRepository;
	
	
	//@Rule
	//public WireMockRule wireMockRule = new WireMockRule(8081);

	
	@BeforeClass
    public static void beforeClass() {
        System.out.println("***************** LEVANTANDO -SERV");
    	_wireMockServer = new WireMockServer(8081);
		_wireMockServer.start();
    }
	 @AfterClass
	 public static void afterClass() {
		   System.out.println("***************** STOP -SERV");
		   _wireMockServer.stop();

	 }
	
	
	@Before
    public void setup() {
     
		_contatoService=  ContatoService.New(_contatoRepository);
    }
	
	
	//@Test
	public void wrm_gma() throws ClientProtocolException, IOException {
		
		System.out.println("GMA =================>inicio");
		
		//WireMockServer wireMockServer = new WireMockServer(8081);
		//wireMockServer.start();
		
		
		configureFor("localhost", 8081);
	 	stubFor(get(urlEqualTo("/v1/gma"))
	 			.willReturn(aResponse()
	 			   .withBody("Teste wireMock - GMA - rota GMA teste")));
		

		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet request = new HttpGet("http://localhost:8081/v1/gma");
		 
		
		HttpResponse httpResponse = httpClient.execute(request);
		
		String responseString = convertResponseToString(httpResponse);
		
		System.out.println("GMA =================>"+responseString);
	 	
		
		System.out.println("GMA =================>parar");
		//try {
		//	Thread.sleep(20000);
		//} catch (InterruptedException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	// wireMockServer.stop();
		
	}
	
	@Test
	public void wrm_gma2() throws ClientProtocolException, IOException {
		
		//WireMockServer wireMockServer = new WireMockServer(8081);
		//wireMockServer.start();
		
		System.out.println("GMA111 =================>inicio");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet request = new HttpGet("http://localhost:8081/api/v1/contatos/alterado");
		 
		
		HttpResponse httpResponse = httpClient.execute(request);
		
		String responseString = convertResponseToString(httpResponse);
		
		System.out.println("GMA =================>"+responseString);
	 	
		
		System.out.println("GMA =================>parar");
		 
	//    wireMockServer.stop();
		
	}
	@Test
	public void wrm_gma_contato() throws ClientProtocolException, IOException {
		
		//WireMockServer wireMockServer = new WireMockServer(8081);
		//wireMockServer.start();
		
		System.out.println("GMA222 =================>inicio");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet request = new HttpGet("http://localhost:8081/api/v1/contatos");
		 
		
		HttpResponse httpResponse = httpClient.execute(request);
		
		String responseString = convertResponseToString(httpResponse);
		
		System.out.println("GMA =================>"+responseString);
	 	
		
		System.out.println("GMA =================>parar");
		 
	//    wireMockServer.stop();
		
	}
 
	
	
	private String convertResponseToString(HttpResponse response) throws IOException {
	    InputStream responseStream = response.getEntity().getContent();
	    Scanner scanner = new Scanner(responseStream, "UTF-8");
	    String responseString = scanner.useDelimiter("\\Z").next();
	    scanner.close();
	    return responseString;
	}
	
	
	@Ignore
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
	
	@Ignore
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
