package edu.mefpd.academy.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mefpd.academy.entity.Alumno;
import edu.mefpd.academy.entity.Curso;
import edu.mefpd.academy.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	Logger log = LoggerFactory.getLogger(CursoController.class);

	@Autowired
	CursoService cursoService;
	
	@Autowired
	Environment environment;


	
	@GetMapping //GET http://localhost:8081/curso
	 public ResponseEntity<Iterable<Curso>> consultarTodos ()
	 {
		 ResponseEntity<Iterable<Curso>> respuesta = null;
		 Iterable<Curso> listaCursos = null;
		 	
		 	log.info("En consultar todos ");
		 	listaCursos = this.cursoService.leerTodos();
		 	respuesta = ResponseEntity.ok(listaCursos);
		 	log.info("RESPUESTA =  " + respuesta);
		 
		 return respuesta;
	 }
	
	
	@GetMapping("/{id}") //GET http://localhost:8081/curso/1
	 public ResponseEntity<Curso> consultarCursosPorID (@PathVariable Long id)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Curso cursoLeido = null;
		 Optional<Curso> oa = Optional.empty();
		 	
		 	log.info("En consultar por ID ");
		 	oa = this.cursoService.leerPorId(id);
		 	if (oa.isPresent())
		 	{
		 		cursoLeido = oa.get();
		 		respuesta = ResponseEntity.ok(cursoLeido);
		 		log.info("Curso encontrado con id " + id + " " + cursoLeido);
		 	} else {
		 		log.info("Curso NO encontrado con id " + id);
		 		respuesta = ResponseEntity.noContent().build();
		 	}

		 
		 return respuesta;
	 }

	
	@PostMapping //POST http://localhost:8081/curso
	 public ResponseEntity<?> insertarCurso (@RequestBody Curso curso)
	 {
		 ResponseEntity<?> respuesta = null;
		 Curso cursoNuevo = null;
		 	
		 	log.info("En insertarCurso ");
		 	cursoNuevo = this.cursoService.alta(curso);
			respuesta = ResponseEntity.status(HttpStatus.CREATED).body(cursoNuevo);
			log.info("Curso nuevo =  " +cursoNuevo);
		 	
		 	

		 
		 return respuesta;
	 }
	
	
	
	@DeleteMapping("/{id}") //POST http://localhost:8081/curso/8
	 public ResponseEntity<Void> borrarCursoPorId (@PathVariable Long id)
	 {
		 ResponseEntity<Void> respuesta = null;
		 	
		 	log.info("En borrarCursoPorId ");
		 	this.cursoService.bajaPorId(id);
		 	respuesta = ResponseEntity.status(HttpStatus.OK).build();

		 
		 return respuesta;
	 }
	
	
	@PutMapping("/{id}") //PUT http://localhost:8081/curso/1
	 public ResponseEntity<Curso> modificarCurso (@RequestBody Curso curso, @PathVariable Long id)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Optional<Curso> oa = Optional.empty();
		 Curso cursoModificado = null;
		 	
		 	log.info("En modificarCurso ");
		 	oa = this.cursoService.modificar(curso, id);
		 	if (oa.isPresent())
		 	{
		 		
		 		cursoModificado = oa.get();
		 		respuesta = ResponseEntity.status(HttpStatus.OK).body(cursoModificado);
		 		
		 		log.info("cursoModificado  " + cursoModificado);
		 	} else {
		 		respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 		log.info("Curso No encotnrado");
		 	}
		 	

		 
		 return respuesta;
	 }
	
	
	@PutMapping("/add-alumnos/{id}") //PUT http://localhost:8081/curso/add-alumnos/1
	 public ResponseEntity<Curso> modificarCursoAddAlumnos (@RequestBody List<Alumno> alumnos, @PathVariable Long id)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Optional<Curso> oa = Optional.empty();
		 Curso cursoModificado = null;
		 	
		 	log.info("En modificarCurso add alumnos ");
		 	oa = this.cursoService.asignarAlumnos(alumnos, id);
		 	if (oa.isPresent())
		 	{
		 		
		 		cursoModificado = oa.get();
		 		respuesta = ResponseEntity.status(HttpStatus.OK).body(cursoModificado);
		 		
		 		log.info("cursoModificado  " + cursoModificado);
		 	} else {
		 		respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 		log.info("Curso No encotnrado");
		 	}
		 	

		 
		 return respuesta;
	 }
	
	
	@PutMapping("/baja-alumno/{id}") //PUT http://localhost:8081/curso/baja-alumno/1
	 public ResponseEntity<Curso> modificarCursoEliminarAlumno (@RequestBody Alumno alumno, @PathVariable Long id)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Optional<Curso> oa = Optional.empty();
		 Curso cursoModificado = null;
		 	
		 	log.info("En modificarCurso eliminar alumno");
		 	oa = this.cursoService.eliminarAlumno(alumno, id);
		 	if (oa.isPresent())
		 	{
		 		
		 		cursoModificado = oa.get();
		 		respuesta = ResponseEntity.status(HttpStatus.OK).body(cursoModificado);
		 		
		 		log.info("cursoModificado  " + cursoModificado);
		 	} else {
		 		respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 		log.info("Curso No encotnrado");
		 	}
		 	

		 
		 return respuesta;
	 }
	
	
	@GetMapping("/obtener-curso-alumno/{idalumno}") //GET http://localhost:8081/curso/1
	 public ResponseEntity<Curso> obtenerCursoAlumno (@PathVariable Long idalumno)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Curso cursoLeido = null;
		 Optional<Curso> oa = Optional.empty();
		 	
		 	log.info("En obtener-curso-alumno ");
		 	oa = this.cursoService.obtenerCursoAlumno(idalumno);
		 	if (oa.isPresent())
		 	{
		 		cursoLeido = oa.get();
		 		respuesta = ResponseEntity.ok(cursoLeido);
		 		log.info("Curso encontrado con id " + idalumno + " " + cursoLeido);
		 	} else {
		 		log.info("Curso NO encontrado con id " + idalumno);
		 		respuesta = ResponseEntity.noContent().build();
		 	}

		 
		 return respuesta;
	 }
	
	
	

}
