package edu.mefpd.academy.msgatewayprofe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsgatewayprofeApplication {
	
	/**
	 *
	 * PASOS PARA CREAR EL GATEWAY
	 * 
	 * 1) CREO PROYECTO CON DEPENDENCIAS DE GATEWAY Y EUREKA CLIENT
	 * 2) CONFIGURO YML/PROPERTIES
	 * 3) ADD ANOTACIÓN @EnableDiscoveryClient
	 *
	 */

	public static void main(String[] args) {
		SpringApplication.run(MsgatewayprofeApplication.class, args);
	}

}
