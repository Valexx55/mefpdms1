package edu.mefpd.academy.msalumnosprofe.security;

import java.util.List;

import org.apache.hc.core5.http.impl.Http1StreamListener;
import org.apache.http.auth.AUTH;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfiguracionSeguridad {
	
	//vamos a definir los usuario de nuestra app
	//USERDETAILS -> Que representa los datos del usuario (usuario, constraseña, rol)
	//USERDETAILSERVICE -> Que ME DA acceso a la info de los usuarios (UserDetail)
	
	@Bean
	public UserDetailsService usuariosEnMemoria ()
	{
		UserDetailsService userDetailsService = null;
		UserDetails usuario1 = null;
		UserDetails usuario2 = null;
		
			usuario1 = User.withUsername("user").password("{noop}user").roles("USER").build();
			usuario2 = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
			List<UserDetails> lusuarios = List.of(usuario1, usuario2);
			
			userDetailsService = new InMemoryUserDetailsManager(lusuarios);
			
			
		return userDetailsService;
		
	}
	
	
	//SecurityFilterChain define qué urls están protegidas y para quién
	@Bean
	public SecurityFilterChain filtroDesarrollo (HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf(c -> c.disable())
						   		.authorizeHttpRequests(auth -> 
						   				auth.requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN")
						   				//.requestMatchers("/alumno/**").authenticated())
						   			    .requestMatchers("/alumno/**").anonymous())
						   				//.requestMatchers("/swagger-ui/**").anonymous()
						   				//.requestMatchers("/v3/api-docs*/**").anonymous()
						   				//.requestMatchers("/swagger-ui.html").anonymous())
						   		.httpBasic(Customizer.withDefaults()).build();
	}
	
	@Bean
	public WebSecurityCustomizer seguridadWeb ()
	{
		return web -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs*/**", "/swagger-ui.html", "/index.html", "/pruebacors.js");
	}
	
	
	

}
