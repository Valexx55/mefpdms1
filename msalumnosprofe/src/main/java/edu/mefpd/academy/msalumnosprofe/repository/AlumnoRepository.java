package edu.mefpd.academy.msalumnosprofe.repository;

import org.springframework.boot.context.properties.source.IterableConfigurationPropertySource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mefpd.academy.mscomunprofe.entity.Alumno;

/**
 * En esta interfaz, se encapsula la interacción con la base de datos
 * 
 * @author usuario
 *
 */

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

	//ya tenemos una implementación de alta baja y lectura de alumnos
	
	//extendemos la funcionalidad con una key word query
	
	//obtenerlosalumnos en un rago de edad
	
	//TODO haced el controller y el service necesario para dar acceso a esta nueva consulta
	public Iterable<Alumno> findByEdadBetween (int edadmin, int edadmax);
	
	
	
	
	
	
}
