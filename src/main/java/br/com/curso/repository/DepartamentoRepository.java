package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
