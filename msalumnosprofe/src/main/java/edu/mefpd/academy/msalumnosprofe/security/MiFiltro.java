package edu.mefpd.academy.msalumnosprofe.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/**")
public class MiFiltro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("HOLA PASA POR AQUï A LA ida");
		chain.doFilter(request, response);
		System.out.println("HOLA PASA POR AQUï A LA VUELTA");
		
	}
	
	

}
