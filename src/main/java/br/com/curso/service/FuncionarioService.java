package br.com.curso.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.curso.model.Funcionario;

public interface FuncionarioService {

	void save(Funcionario funcionario);

	void update(Funcionario funcionario);

	void delete(Long id);

	Optional<Funcionario> findById(Long id);

	List<Funcionario> findAll();

	List<Funcionario> findByName(String nome);

	List<Funcionario> findByCargo(Long id);

	List<Funcionario> findByDatas(LocalDate entrada, LocalDate saida);
}
