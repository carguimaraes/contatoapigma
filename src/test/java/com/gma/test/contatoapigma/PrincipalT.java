package com.gma.test.contatoapigma;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;

public class PrincipalT {

	public static void main(String[] args) {
	System.out.println("GMA =================>inicio");
		
		WireMockServer wireMockServer = new WireMockServer(8081);
		wireMockServer.start();
		
		configureFor("http://wiremock.org", 8081);
		
		stubFor(get(urlEqualTo("/gmagma"))
				.willReturn(aResponse()
				.withBody("Teste wireMock - GMA")));
		
		System.out.println("GMA =================>inicio2");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wireMockServer.stop();
		
		

	}

}
