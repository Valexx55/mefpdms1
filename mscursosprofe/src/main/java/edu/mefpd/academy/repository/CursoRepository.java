package edu.mefpd.academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.mefpd.academy.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
	
	//TODO Para mañana viernes
	//completamos este nuevo servicio
	//com interna microservicios de una misma nube OpenFeign
	//Test
	//Seguridad
	//Paginación
	//foto
	
	//una consulta nativa
	//dado un id de alumno, que nos diga en qué curso está (si está en alguno)
	@Query(value = "select * from cursos where id = (select curso_id from cursos_alumnos where alumnos_id = ?1)", nativeQuery = true)
	public Optional<Curso> obtenerCursoAlumno (Long idalumno);

}
