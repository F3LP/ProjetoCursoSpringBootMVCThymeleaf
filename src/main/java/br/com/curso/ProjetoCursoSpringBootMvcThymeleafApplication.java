package br.com.curso;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso.controller.DepartamentoController;
import br.com.curso.model.Departamento;
import br.com.curso.repository.DepartamentoRepository;
import br.com.curso.service.DepartamentoService;
import br.com.curso.service.DepartamentoServiceImpl;

@SpringBootApplication
public class ProjetoCursoSpringBootMvcThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCursoSpringBootMvcThymeleafApplication.class, args);
	}
}
