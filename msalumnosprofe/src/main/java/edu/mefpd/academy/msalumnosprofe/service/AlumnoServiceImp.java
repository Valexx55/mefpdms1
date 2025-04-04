package edu.mefpd.academy.msalumnosprofe.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mefpd.academy.msalumnosprofe.repository.AlumnoRepository;
import edu.mefpd.academy.mscomunprofe.entity.Alumno;


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
	@Transactional
	public Alumno alta(Alumno alumno) {
	//tengo q usar la base de datos
		return this.alumnoRepository.save(alumno);
		
	}

	@Override
	@Transactional
	public void bajaPorId(Long id) {
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Alumno> modificar(Alumno alumno, Long id) {
		Optional<Alumno> oa = Optional.empty();
			//1 leer
			oa = this.alumnoRepository.findById(id);
			if (oa.isPresent())
			{
				//2 modificar los atributos
				Alumno alumno_leido = oa.get();//ESTADO PERSISTENTE
				//alumno_leido.setNombre(alumno.getNombre());
				BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");
				//3 escribilo - AUTOMÁTICO 
				//this.alumnoRepository.save(alumno_leido);
				oa = Optional.of(alumno_leido);
				
			}

		
		return oa;
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

	@Override
	@Transactional
	public Iterable<Alumno> findByEdadBetween(int edadmin, int edadmax) {
		
		return this.alumnoRepository.findByEdadBetween(edadmin, edadmax);
	}

}
