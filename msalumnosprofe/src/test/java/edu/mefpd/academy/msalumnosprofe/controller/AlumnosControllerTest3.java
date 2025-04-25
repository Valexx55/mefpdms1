package edu.mefpd.academy.msalumnosprofe.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import edu.mefpd.academy.msalumnosprofe.ClienteFeignCurso;
import edu.mefpd.academy.msalumnosprofe.MsalumnosprofeApplication;
import edu.mefpd.academy.msalumnosprofe.controller.AlumnosController;
import edu.mefpd.academy.msalumnosprofe.security.ConfiguracionSeguridad;
import edu.mefpd.academy.msalumnosprofe.service.AlumnoService;
import edu.mefpd.academy.msalumnosprofe.service.ElTiempoService;
import edu.mefpd.academy.mscomunprofe.entity.Alumno;

@WebMvcTest(AlumnosController.class)//levanto sólo el contexto de alumno controller
@Import(ConfiguracionSeguridad.class)//pero también tengo en cuenta la seguridad
public class AlumnosControllerTest3 {
	
	@Autowired
	private MockMvc mockMvc; // el objeto con el que lanzamos las peticiones HTTP
	
	//@MockBean deprecado
	@MockitoBean
	AlumnoService alumnoService; //es un servicio de mentirijilla--> todo: programar su comportamiento
	
	//ES NECESARIO SIMULAR LOS BEANS INYECTADOS EN EL CONTROLLER
	//AUNQUE NO VAYAMOS A necesitarlos PARA INSTANCIAR CORRECTAMENTE
	@MockitoBean
	ElTiempoService elTiempoService; //es un servicio de mentirijilla--> todo: programar su comportamiento
	
	@MockitoBean
	ClienteFeignCurso clienteFeignCurso;


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

		//this.mockMvc.perform(get("/alumno/2")).
		//add seguridad a la petición
		this.mockMvc.perform(get("/alumno/2").with(httpBasic("admin", "admin"))).
		andDo(print()).
		andExpect(status().isOk()).
		andExpect(content().contentType("application/json")).
		andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juan")).
		andExpect(MockMvcResultMatchers.jsonPath("$.edad").value("22"));
	}


}
