package edu.mefpd.academy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.mefpd.academy.model.Alumno;
import edu.mefpd.academy.repository.AlumnoRepository;

@Service
public class AlumnoServiceImp implements AlumnoService {
	
	AlumnoRepository alumnoRepository;//trabajo con la bd a partir de este objecto
	

	@Override
	public Alumno alta(Alumno alumno) {
	//tengo q usar la base de datos
		this.alumnoRepository.save(alumno);
		return null;
	}

	@Override
	public void bajaPorId(Long id) {
		// TODO Auto-generated method stub
		this.alumnoRepository.deleteById(id);
		//tengo q usar la base de datos
	}

	@Override
	public Optional<Alumno> modificar(Alumno alumno, Long id) {
		// TODO Auto-generated method stub
		//tengo q usar la base de datos
		return Optional.empty();
	}

	@Override
	public Optional<Alumno> leerPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Iterable<Alumno> leerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
