package br.com.curso.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.curso.model.Cargo;
import br.com.curso.model.Funcionario;
import br.com.curso.model.UF;
import br.com.curso.service.CargoService;
import br.com.curso.service.FuncionarioService;
import br.com.curso.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private FuncionarioService funcionarioService;
	private CargoService cargoService;

	@Autowired
	public FuncionarioController(FuncionarioService funcionarioService, CargoService cargoService) {
		this.funcionarioService = funcionarioService;
		this.cargoService = cargoService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
	}
		
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findAll());
		return "funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.save(funcionario);
		attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.findById(id).get());
		return "funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.update(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.delete(id);
		attr.addFlashAttribute("sucess", "Funcionário removido com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		System.out.println(funcionarioService.findByName(nome));
		model.addAttribute("funcionarios", funcionarioService.findByName(nome));
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		System.out.println(funcionarioService.findByCargo(id));
		model.addAttribute("funcionarios", funcionarioService.findByCargo(id));
		return "funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
								@RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
								ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.findByDatas(entrada, saida));
		return "funcionario/lista";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.findAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
}
