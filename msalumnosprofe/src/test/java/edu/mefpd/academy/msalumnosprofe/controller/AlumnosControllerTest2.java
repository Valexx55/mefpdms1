package edu.mefpd.academy.msalumnosprofe.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.mefpd.academy.msalumnosprofe.MsalumnosprofeApplication;

@SpringBootTest(classes = {MsalumnosprofeApplication.class})//no encuentra la clase de configuración sin este atributo
@AutoConfigureMockMvc // mockeo el servidor
public class AlumnosControllerTest2 {
	
	/**
	 *  La idea es testear una api REST de forma «interna» sin tener que desplegar la aplicación en un servidor, 
	 *  pero también de forma realista efectuando las llamadas a la misma tal y como lo harían los consumidores del servicio.
	 */
	
	@Autowired
	private MockMvc mockMvc; // el objeto con el que lanzamos las peticiones HTTP
	
	@Autowired
	ObjectMapper om;//es para serializar a JSON el alumno
	
	
	//TEST POST CORRECTO
	@Test
	public void insertarAlumnoTest() throws Exception {
	
		
		ObjectNode objectNode = om.createObjectNode();//nuestro alumno
		objectNode.put("nombre" , "Nacho");
		objectNode.put("apellido" , "Moreno");
		objectNode.put("email" , "nacho@rm.es");
		objectNode.put("edad" , 15);
		
		// serializar este alumno
		String alumno_json = objectNode.toString();

		/*mockMvc.perform(post("/alumno").contentType(MediaType.APPLICATION_JSON).content(alumno_json))
				.andExpect(status().isCreated()).andExpect(content().contentType("application/json"));*/
		//ADD CREDENCIALES
		mockMvc.perform(post("/alumno").with(httpBasic("admin", "admin")).contentType(MediaType.APPLICATION_JSON).content(alumno_json))
		.andExpect(status().isCreated()).andExpect(content().contentType("application/json"));

	}
	
	//TODO HACER un MÉTODO PARA EL CASO 400 bad request poniendo datos incorrectos
	@Test
	public void insertarAlumnoTestError() throws Exception {	
		
		ObjectNode objectNode = om.createObjectNode();//nuestro alumno
		objectNode.put("nombre" , "Na");
		objectNode.put("apellido" , "");
		objectNode.put("email" , "nachorm.es");
		objectNode.put("edad" , 9);     //tipo de dato incorrecto
		
		
		// serializar este alumno
		String alumno_json = objectNode.toString();

		/*mockMvc.perform(post("/alumno").contentType(MediaType.APPLICATION_JSON).content(alumno_json))
				.andExpect(status().isBadRequest());*/

		//ADD CREDENCIALES
		mockMvc.perform(post("/alumno").with(httpBasic("admin", "admin")).contentType(MediaType.APPLICATION_JSON).content(alumno_json))
		.andExpect(status().isBadRequest());
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
