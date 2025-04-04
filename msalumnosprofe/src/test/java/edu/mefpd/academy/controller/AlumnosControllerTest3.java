package edu.mefpd.academy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.mefpd.academy.MsalumnosprofeApplication;
import edu.mefpd.academy.entity.Alumno;
import edu.mefpd.academy.service.AlumnoService;

@WebMvcTest(value = AlumnosController.class, useDefaultFilters = false)//levanto sólo el contexto de alumno
public class AlumnosControllerTest3 {
	
	@Autowired
	private MockMvc mockMvc; // el objeto con el que lanzamos las peticiones HTTP
	
	//@MockBean deprecado
	@MockitoBean
	AlumnoService alumnoService; //es un servicio de mentirijilla--> todo: programar su comportamiento


	//Ejemplo de TEST Unitario, Simulando (MOCK) el servicio y por tanto falseando la interacción con la base de datos
	@Test
	public void getServicioMockeado() throws Exception {
		
		Alumno alumno = new Alumno();
		alumno.setId(2l);
		alumno.setNombre("Juan");
		alumno.setApellido("Moreno");
		alumno.setEdad(22);
		alumno.setEmail("juanmo@mail.es");

		
		//programamos el funcionamiento del mock
		when(alumnoService.leerPorId(2l)).thenReturn(Optional.of(alumno));

		this.mockMvc.perform(get("/alumno/2")).
		andDo(print()).
		andExpect(status().isOk()).
		andExpect(content().contentType("application/json")).
		andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan")).
		andExpect(MockMvcResultMatchers.jsonPath("$.edad").value("22"));
	}


}
