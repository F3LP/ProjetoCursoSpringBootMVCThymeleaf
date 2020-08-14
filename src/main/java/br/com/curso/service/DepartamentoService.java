package br.com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.curso.model.Departamento;

public interface DepartamentoService {

	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Long id);
	
	Optional<Departamento> findById(Long id);
	
	List<Departamento> findAll();
	
	boolean departamentoTemCargos(Long id);
	
}
	
