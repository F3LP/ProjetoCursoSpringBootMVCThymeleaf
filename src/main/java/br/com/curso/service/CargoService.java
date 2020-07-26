package br.com.curso.service;

import java.util.List;
import java.util.Optional;

import br.com.curso.model.Cargo;

public interface CargoService {

	void save(Cargo cargo);

	void update(Cargo cargo);

	void delete(Long id);

	Optional<Cargo> findById(Long id);

	List<Cargo> findAll();

	boolean cargoTemFuncionarios(Long id);
}
