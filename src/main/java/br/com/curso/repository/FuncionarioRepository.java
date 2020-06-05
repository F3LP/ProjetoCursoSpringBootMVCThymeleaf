package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
