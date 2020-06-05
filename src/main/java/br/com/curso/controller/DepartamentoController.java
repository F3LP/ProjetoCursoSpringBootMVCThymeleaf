package br.com.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.curso.model.Departamento;
import br.com.curso.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	private DepartamentoService service;

	@Autowired
	public DepartamentoController(DepartamentoService service) {
		this.service = service;
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/departamento/lista");
		mv.addObject("departamentos", service.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento) {
		service.save(departamento);
		return "redirect:/departamentos/cadastrar";
	}
}

