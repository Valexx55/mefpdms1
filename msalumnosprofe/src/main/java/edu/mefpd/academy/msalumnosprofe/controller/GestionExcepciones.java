package edu.mefpd.academy.msalumnosprofe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * esta clase es un "listener" de las excepciones en mi proyecto
 * dirigir cualquier fallo, aquí
 * simplemente con antotaciones Programación Orientada a Aspectos
 * @author usuario
 *
 */
@RestControllerAdvice(basePackages = {"edu.mefpd.academy"})
public class GestionExcepciones {
	
	
	Logger log = LoggerFactory.getLogger(GestionExcepciones.class);
	
	//para cada tipo de excepción, puedo definir un método específico para su tratamiento
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> gestionNullPointer(NullPointerException npe)
	{
		ResponseEntity<String> rpe = null;
		
			String mensaje = npe.getMessage();
			rpe = ResponseEntity.internalServerError().body(mensaje);
			log.error("ERROR tipo null", npe);
		
		return rpe;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> gestionExcepcionNoIdentificada(Throwable error)
	{
		ResponseEntity<String> rpe = null;
		
			String mensaje = error.getMessage();
			rpe = ResponseEntity.internalServerError().body(mensaje);
			log.error("ERROR desconocido", error);
		
		return rpe;
	}
	
	
	
	
	

}
