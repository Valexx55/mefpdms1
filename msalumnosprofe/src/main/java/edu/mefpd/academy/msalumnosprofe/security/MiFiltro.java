package edu.mefpd.academy.msalumnosprofe.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/**")
public class MiFiltro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("HOLA PASA POR AQUï A LA ida");
		HttpServletRequest hr = (HttpServletRequest)request;
		String cadauth = hr.getHeader("Authorization");
		System.out.println("TOKEN BASIC "+  cadauth);
		byte[] credenciales = Base64.getDecoder().decode(cadauth.substring(6));
		String caddesc = new String(credenciales, StandardCharsets.UTF_8);
		System.out.println(caddesc);
		
		chain.doFilter(request, response);
		System.out.println("HOLA PASA POR AQUï A LA VUELTA");
		
	}
	
	

}
