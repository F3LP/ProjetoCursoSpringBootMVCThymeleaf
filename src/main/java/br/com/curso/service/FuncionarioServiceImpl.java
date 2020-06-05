package br.com.curso.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.model.Funcionario;
import br.com.curso.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioRepository repository;

	@Autowired
	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Funcionario funcionario) {
		repository.save(funcionario);
	}

	@Override
	public void update(Funcionario funcionario) {
		Objects.nonNull(funcionario);
		repository.save(funcionario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);		
	}

	@Override
	public Optional<Funcionario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.findAll();
	}
	
	
}
