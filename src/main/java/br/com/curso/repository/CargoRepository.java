package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
