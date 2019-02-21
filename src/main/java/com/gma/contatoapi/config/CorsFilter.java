package com.gma.contatoapi.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/*
 * 2019-02-21
 * GMA - Carlos A L M Guimaraes
 * 
 */

@Component
public class CorsFilter implements Filter {


	//TODO testar
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	System.out.println("nooooooooooooooooooo filtro");
     
        HttpServletResponse _response = (HttpServletResponse) response;
        _response.setHeader("Access-Control-Allow-Origin", "http://localhost:*");
        _response.setHeader("Access-Control-Allow-Credentials", "true");
        _response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        _response.setHeader("Access-Control-Max-Age", "3600");
        _response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-XSRF-Token, X-XSS-Protection, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        chain.doFilter(request, response);
        
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

   }

