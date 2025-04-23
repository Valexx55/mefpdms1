package edu.mefpd.academy.msalumnosprofe.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.mefpd.academy.msalumnosprofe.ClienteFeignCurso;
import edu.mefpd.academy.msalumnosprofe.service.AlumnoService;
import edu.mefpd.academy.msalumnosprofe.service.ElTiempoService;
import edu.mefpd.academy.mscomunprofe.entity.Alumno;
import edu.mefpd.academy.mscomunprofe.entity.Curso;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

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
@CrossOrigin(originPatterns = {"*"}, methods = {RequestMethod.GET})
@RestController // JSON como formato de la información intercambiada
@RequestMapping("/alumno") // todo lo que sea /alumno va a esta clase
public class AlumnosController {

	Logger log = LoggerFactory.getLogger(AlumnosController.class);

	@Autowired
	AlumnoService alumnoService;
	
	@Autowired//(required = false)
	ElTiempoService elTiempoService;
	
	@Autowired
	ClienteFeignCurso clienteFeignCurso;
	
	@Value("${instancia}")
	String instancia;
	
	@Autowired
	Environment environment;
	
	@Value("${mientorno}")
	String mientorno;
	
	
	
	@PostConstruct
	public void postCreacion ()
	{
		log.debug("Entorno = " + this.mientorno);
	}
	
	@GetMapping("/obtener-tiempo-aemet/{idmunicipio}")
	public ResponseEntity<?> obtenerTiempoViaAemet (@PathVariable String idmunicipio)
	{
		ResponseEntity<?> responseEntity = null;
		String resp = null;
		
			resp = this.elTiempoService.obtenerPrediccionEspecificaPorMunicipio(idmunicipio);
			responseEntity = ResponseEntity.ok(resp);
		
		return responseEntity;
		
	}

	@GetMapping("/obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	 public Alumno obtenerAlumnoTest ()
	 {
		 Alumno a = null;
			 
			a =  new Alumno("Juan", 28);////ESTADO TRANSIENT
		 	log.info("ALUMNO log info = " +a);//TODO configurar el log
		 	log.debug("ALUMNO log debug  = " +a);//TODO configurar el log
		 	
		 	//IndexOutBoundsException
		 	//char i = a.getNombre().charAt(4);
		 	
		 	//provocamos una null pointer exception
		 	//Alumno b = null;
		 	//b.getApellido();
		 
		 return a;
	 }
	
	@Operation(description = "Este método recupera la lista completa de alumnos en el sistema")
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
		 	log.info("Está siendo atentido por " + this.instancia + " - " + environment.getProperty("local.server.port"));
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
	 public ResponseEntity<?> insertarAlumno (@Valid @RequestBody Alumno alumno, BindingResult bindingResult)
	 {
		 ResponseEntity<?> respuesta = null;
		 Alumno alumnoNuevo = null;
		 	
		 	log.info("En insertarAlumno ");
		 	if (bindingResult.hasErrors())
		 	{
		 		log.info("El Alumno presenta errores ");
		 		List<ObjectError> listaerrores  = bindingResult.getAllErrors();
		 		listaerrores.forEach(error -> {
		 			log.error(error.toString());
		 		});
		 		
		 		/*List<String> listaerrores2  = bindingResult.getFieldErrors().stream().map(error -> error.getField()+ " "+ error.getDefaultMessage())
		 	              .collect(Collectors.toList());*/
		 		
		 		
		 		
		 		/*List<FieldError> fieldErrorList = bindingResult.getFieldErrors().stream()
		 	              .map(error -> error.getField()+ error.getDefaultMessage())
		 	              .collect(Collectors.toList());

		 	            //UpdateUserResponse updateResponse = new UpdateUserResponse(fieldErrorList);*/
		 		respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaerrores);
		 		
		 	} else {
		 		log.info("El Alumno es correcto (sin errores)");
		 		alumnoNuevo = this.alumnoService.alta(alumno);
			 	respuesta = ResponseEntity.status(HttpStatus.CREATED).body(alumnoNuevo);
			 	log.info("Alumno nuevo =  " +alumnoNuevo);
		 	}
		 	

		 
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
	
	@GetMapping("/rangoEdad") //GET http://localhost:8081/alumno/rangoEdad?edadmin=10&edadmax=50
	 public ResponseEntity<Iterable<Alumno>> obtenerAlumnosEnRangoEdad (
			 @RequestParam(name = "edadmin", required = true) int edadmin, 
			 @RequestParam(name = "edadmax", required = true) int edadmax)
	 {
		 ResponseEntity<Iterable<Alumno>> respuesta = null;
		 Iterable<Alumno> listaAlumnos = null;
		 	
		 	log.info("En consultar todos ");
		 	listaAlumnos = this.alumnoService.findByEdadBetween(edadmin, edadmax);
		 	respuesta = ResponseEntity.ok(listaAlumnos);
		 	log.info("RESPUESTA =  " + respuesta);
		 
		 return respuesta;
	 }
	
	
	@GetMapping("/obtener-curso-alumno-via-feign/{idalumno}") //GET 
	 public ResponseEntity<Curso> obtenerCursoAlumnoViaFeign (@PathVariable Long idalumno)
	 {
		 ResponseEntity<Curso> respuesta = null;
		 Curso cursoLeido = null;
		 Optional<Curso> oa = Optional.empty();
		 	
		 	log.info("En obtenerCursoAlumnoViaFeign ");
		 	oa = this.clienteFeignCurso.obtenerCursoAlumno(idalumno);
		 	if (oa.isPresent())
		 	{
		 		cursoLeido = oa.get();
		 		respuesta = ResponseEntity.ok(cursoLeido);
		 		log.info("Alumno con curso " + cursoLeido);
		 	} else {
		 		log.info("Alumno sin curso " );
		 		respuesta = ResponseEntity.noContent().build();
		 	}

		 
		 return respuesta;
	 }
	
	
	
	
	
	
	
	
	

}
