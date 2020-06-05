package br.com.curso.service;

import java.util.List;
import java.util.Optional;

import br.com.curso.model.Funcionario;

public interface FuncionarioService {

	void save(Funcionario funcionario);

	void update(Funcionario funcionario);

	void delete(Long id);

	Optional<Funcionario> findById(Long id);

	List<Funcionario> findAll();
}
