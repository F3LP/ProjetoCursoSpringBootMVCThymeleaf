package br.com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.curso.model.Cargo;

public interface CargoService {

	void save(Cargo cargo);

	void update(Cargo cargo);

	void delete(Long id);

	Optional<Cargo> findById(Long id);
	
	Page<Cargo> findAll(int page);
	
	Page<Cargo> findAll(Pageable pageable);
	
	boolean cargoTemFuncionarios(Long id);

	List<Cargo> findAll();
	
}
