package edu.mefpd.academy.msalumnosprofe.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

}
