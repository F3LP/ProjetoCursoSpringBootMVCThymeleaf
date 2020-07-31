package br.com.curso.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.curso.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Query(value = "select f from Funcionario f where f.nome like concat('%', ?1, '%')")
	List<Funcionario> findByName(String nome);

	@Query(value = "select f from Funcionario f where f.cargo.id = ?1")
	List<Funcionario> findByCargo(Long id);

	@Query(value = "select f from Funcionario f where f.dataEntrada >= ?1 and f.dataSaida <= ?2 order By f.dataEntrada asc")
	List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	@Query(value = "select f from Funcionario f where f.dataEntrada = ?1 order By f.dataEntrada asc")
	List<Funcionario> findByDataEntrada(LocalDate entrada);

	@Query(value = "select f from Funcionario f where f.dataSaida = ?1 order By f.dataSaida asc")
	List<Funcionario> findByDataSaida(LocalDate saida);

}
