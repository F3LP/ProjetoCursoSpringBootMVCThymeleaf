package br.com.curso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		repository.save(funcionario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Funcionario> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Funcionario> findByName(String nome) {
		return repository.findByName(nome);
	}

	@Override
	public List<Funcionario> findByCargo(Long id) {
		return repository.findByCargo(id);
	}

	@Override
	public List<Funcionario> findByDatas(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida != null) {
			return repository.findByDataEntradaDataSaida(entrada, saida);
		} else if(entrada != null) {
			return repository.findByDataEntrada(entrada);
		} else if(saida != null) {
			return repository.findByDataSaida(saida);
		} else {
			return new ArrayList<>();
		}
	}
	
}
