package edu.mefpd.academy.mscursosprofe.service;

import java.util.List;
import java.util.Optional;

import edu.mefpd.academy.mscomunprofe.entity.Alumno;
import edu.mefpd.academy.mscomunprofe.entity.Curso;


public interface CursoService {
	
public Curso alta (Curso curso);
	
	public void bajaPorId (Long id);
	
	public Optional<Curso> modificar (Curso curso, Long id);
	
	public Optional<Curso> leerPorId (Long id);
	
	public Iterable<Curso> leerTodos ();
	
	public Optional<Curso>  asignarAlumnos (List<Alumno> lalumnos, Long idcurso);
	
	public Optional<Curso> eliminarAlumno (Alumno a, Long idcurso);
	
	public Optional<Curso> obtenerCursoAlumno (Long idalumno);

}
