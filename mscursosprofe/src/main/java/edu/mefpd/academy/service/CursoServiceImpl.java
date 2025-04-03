package edu.mefpd.academy.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mefpd.academy.model.Curso;
import edu.mefpd.academy.repository.CursoRepository;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {
	
	@Autowired
	CursoRepository cursoRepository;

	@Override
	public Curso alta(Curso curso) {

		return this.cursoRepository.save(curso);

	}

	@Override
	public void bajaPorId(Long id) {
		this.cursoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Curso> modificar(Curso curso, Long id) {
		Optional<Curso> oc = Optional.empty();
			
			oc =  this.cursoRepository.findById(id);
			if (oc.isPresent()) {
				Curso cursoleido =  oc.get();
				BeanUtils.copyProperties(curso, cursoleido, "id");
				oc = Optional.of(cursoleido);
			}
			
		return oc;
	}

	@Override
	public Optional<Curso> leerPorId(Long id) {
		return this.cursoRepository.findById(id);
	}

	@Override
	public Iterable<Curso> leerTodos() {
		
		return this.cursoRepository.findAll();
	}

}
