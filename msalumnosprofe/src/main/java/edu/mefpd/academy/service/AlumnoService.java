package edu.mefpd.academy.service;

import java.util.Optional;

import edu.mefpd.academy.model.Alumno;

/**
 * Aquí, defino cuál es la operativa de mi aplicación
 * 
 * @author usuario
 *
 */
public interface AlumnoService {

	public Alumno alta (Alumno alumno);
	
	public void bajaPorId (Long id);
	
	public Optional<Alumno> modificar (Alumno alumno, Long id);
	
	public Optional<Alumno> leerPorId (Long id);
	
	public Iterable<Alumno> leerTodos ();
	
	public Iterable<Alumno> findByEdadBetween (int edadmin, int edadmax);
	
}
