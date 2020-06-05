package br.com.curso.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.model.Cargo;
import br.com.curso.repository.CargoRepository;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	private CargoRepository repository;

	@Autowired
	public CargoServiceImpl(CargoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Cargo cargo) {
		repository.save(cargo);
	}

	@Override
	public void update(Cargo cargo) {
		Objects.nonNull(repository.findById(cargo.getId()));
		repository.save(cargo);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Cargo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Cargo> findAll() {
		return repository.findAll();
	}

}
