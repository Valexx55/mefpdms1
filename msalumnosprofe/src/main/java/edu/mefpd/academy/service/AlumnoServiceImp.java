package edu.mefpd.academy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mefpd.academy.model.Alumno;
import edu.mefpd.academy.repository.AlumnoRepository;



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
