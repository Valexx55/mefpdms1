package edu.mefpd.academy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mefpd.academy.model.Alumno;
import edu.mefpd.academy.repository.AlumnoRepository;


@Service
public class AlumnoServiceImp implements AlumnoService {
	
	/*
	 * lo que hace Spring al arrancar
	 * 
	 * 1) alumnoserviceImp = new AlumnoServiceImp //Inversión de Control
	 * 2) alumnoRepository = new AlumnoRepository //IOC
	 * 3) Composición "inyección"
	 * 	alumnoserviceImp.alumnoRepository = alumnoRepository
	 * */
	
	
	@Autowired//dame un alumnno repository
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
	@Transactional(readOnly = true)
	public Optional<Alumno> leerPorId(Long id) {
		
		return this.alumnoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> leerTodos() {
		
		return this.alumnoRepository.findAll();
	}

}
