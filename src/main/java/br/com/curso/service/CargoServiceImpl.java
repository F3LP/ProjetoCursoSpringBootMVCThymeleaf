package br.com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		repository.save(cargo);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cargo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cargo> findAll(int page) {
		Pageable pageable = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.ASC, "nome"));
		return repository.findAll(pageable);
	}

	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if (findById(id).get().getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public List<Cargo> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@Override
	public Page<Cargo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
