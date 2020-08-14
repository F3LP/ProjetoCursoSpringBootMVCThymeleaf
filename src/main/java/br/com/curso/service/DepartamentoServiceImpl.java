package br.com.curso.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.model.Departamento;
import br.com.curso.repository.DepartamentoRepository;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {

	private DepartamentoRepository repository;

	@Autowired
	public DepartamentoServiceImpl(DepartamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Departamento departamento) {
		repository.save(departamento);
	}

	@Override
	public void update(Departamento departamento) {
		repository.save(departamento);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Departamento> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean departamentoTemCargos(Long id) {
		if (findById(id).get().getCargos().isEmpty()) {
			return false;
		}
		return true;
	}

}
