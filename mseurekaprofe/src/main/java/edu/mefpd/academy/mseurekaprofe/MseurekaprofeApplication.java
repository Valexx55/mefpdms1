package edu.mefpd.academy.mseurekaprofe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //activamos la librería de Eureka
public class MseurekaprofeApplication {
	
	/**
	 * PASOS PARA CREAR EUREKA
	 * 
	 * 1) CREAMOS EL PROYECTO SPRING STARTER CON DEPENDENCIA DE EUREKA SERVER
	 * 2) CONFIGURO PROPERTIES
	 * 3) IMPORTO DEPENDENCIA GLASSFISH JAX B POM
	 * 4) INCLUIR ANOTACIÓN MAIN @EnableEurekaServer
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(MseurekaprofeApplication.class, args);
	}

}
