package edu.mefpd.academy.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mefpd.academy.model.Alumno;
import edu.mefpd.academy.service.AlumnoService;

/**
 * Esta clase, recibe las peticiones HTTP, que permiten
 * 
 * a)Alta de alummnos - POST 
 * b)Baja - DELETE 
 * c)Modificiación - PUT 
 * d)Consulta por ID - * GET
 * 	 Consulta de todos
 * 
 * API crud / clab REST - JSON
 * 
 * @author usuario
 *
 */
@RestController // JSON como formato de la información intercambiada
@RequestMapping("/alumno") // todo lo que sea /alumno va a esta clase
public class AlumnosController {

	Logger log = LoggerFactory.getLogger(AlumnosController.class);

	@Autowired
	AlumnoService alumnoService;

	@GetMapping("obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	 public Alumno obtenerAlumnoTest ()
	 {
		 Alumno a = null;
			 
			a =  new Alumno("Juan", 28);////ESTADO TRANSIENT
		 	log.info("ALUMNO log info = " +a);//TODO configurar el log
		 	log.debug("ALUMNO log debug  = " +a);//TODO configurar el log
		 	
		 	//IndexOutBoundsException
		 	char i = a.getNombre().charAt(4);
		 	
		 	//provocamos una null pointer exception
		 	Alumno b = null;
		 	b.getApellido();
		 
		 return a;
	 }
	
	@GetMapping //GET http://localhost:8081/alumno
	 public ResponseEntity<Iterable<Alumno>> consultarTodos ()
	 {
		 ResponseEntity<Iterable<Alumno>> respuesta = null;
		 Iterable<Alumno> listaAlumnos = null;
		 	
		 	log.info("En consultar todos ");
		 	listaAlumnos = this.alumnoService.leerTodos();
		 	respuesta = ResponseEntity.ok(listaAlumnos);
		 	log.info("RESPUESTA =  " + respuesta);
		 
		 return respuesta;
	 }
	
	
	@GetMapping("/{id}") //GET http://localhost:8081/alumno/1
	 public ResponseEntity<Alumno> consultarAlumnosPorID (@PathVariable Long id)
	 {
		 ResponseEntity<Alumno> respuesta = null;
		 Alumno alumnoLeido = null;
		 Optional<Alumno> oa = Optional.empty();
		 	
		 	log.info("En consultar por ID ");
		 	oa = this.alumnoService.leerPorId(id);
		 	if (oa.isPresent())
		 	{
		 		alumnoLeido = oa.get();
		 		respuesta = ResponseEntity.ok(alumnoLeido);
		 		log.info("Alumno encontrado con id " + id + " " + alumnoLeido);
		 	} else {
		 		log.info("Alumno NO encontrado con id " + id);
		 		respuesta = ResponseEntity.noContent().build();
		 	}

		 
		 return respuesta;
	 }

	
	@PostMapping //POST http://localhost:8081/alumno
	 public ResponseEntity<Alumno> insertarAlumno (@RequestBody Alumno alumno)
	 {
		 ResponseEntity<Alumno> respuesta = null;
		 Alumno alumnoNuevo = null;
		 	
		 	log.info("En insertarAlumno ");
		 	alumnoNuevo = this.alumnoService.alta(alumno);
		 	respuesta = ResponseEntity.status(HttpStatus.CREATED).body(alumnoNuevo);
		 	log.info("Alumno nuevo =  " +alumnoNuevo);

		 
		 return respuesta;
	 }
	
	
	
	@DeleteMapping("/{id}") //POST http://localhost:8081/alumno/8
	 public ResponseEntity<Void> borrarAlumnoPorId (@PathVariable Long id)
	 {
		 ResponseEntity<Void> respuesta = null;
		 	
		 	log.info("En borrarAlumnoPorId ");
		 	this.alumnoService.bajaPorId(id);
		 	respuesta = ResponseEntity.status(HttpStatus.OK).build();

		 
		 return respuesta;
	 }
	
	
	@PutMapping("/{id}") //PUT http://localhost:8081/alumno/1
	 public ResponseEntity<Alumno> modificarAlumno (@RequestBody Alumno alumno, @PathVariable Long id)
	 {
		 ResponseEntity<Alumno> respuesta = null;
		 Optional<Alumno> oa = Optional.empty();
		 Alumno alumnoModificado = null;
		 	
		 	log.info("En modificarAlumno ");
		 	oa = this.alumnoService.modificar(alumno, id);
		 	if (oa.isPresent())
		 	{
		 		
		 		alumnoModificado = oa.get();
		 		respuesta = ResponseEntity.status(HttpStatus.OK).body(alumnoModificado);
		 		
		 		log.info("alumnoModificado  " + alumnoModificado);
		 	} else {
		 		respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 		log.info("Alumno No encotnrado");
		 	}
		 	

		 
		 return respuesta;
	 }

}
