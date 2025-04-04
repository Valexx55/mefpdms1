package edu.mefpd.academy.msalumnosprofe;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.mefpd.academy.mscomunprofe.entity.Curso;

/**
 * Esta clase es la que se va a comunicar con el MS de CURSOS
 * @author usuario
 *
 */
@FeignClient(name = "mscursosprofe")//indicamos el nombre del microservicio que somos clientes
public interface ClienteFeignCurso {
	
	@GetMapping("/curso/obtener-curso-alumno/{idalumno}")
	public Optional<Curso> obtenerCursoAlumno (@PathVariable Long idalumno);

}
