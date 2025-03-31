package edu.mefpd.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mefpd.academy.model.Alumno;

/**
 * Esta clase, recibe las peticiones HTTP, que permiten
 * 
 * a)Alta de almmnos - POST
 * b)Baja - DELETE
 * c)Modificiación - PUT
 * d)Consulta - GET
 * 
 * API crud / clab 
 * REST - JSON 
 * 
 * @author usuario
 *
 */
@RestController //JSON como formato de la información intercambiada
public class AlumnosController {
	
	 @GetMapping("obtener-alumno-test") //GET http://localhost:8081/obtener-alumno-test
	 public Alumno obtenerAlumnoTest ()
	 {
		 Alumno a =  new Alumno("Juan", 28);
		 return a;
	 }

}
