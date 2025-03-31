package edu.mefpd.academy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mefpd.academy.model.Alumno;

/**
 * En esta interfaz, se encapsula la interacción con la base de datos
 * 
 * @author usuario
 *
 */

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

	//ya tenemos una implementación de alta baja y lectura de alumnos
}
