package br.com.curso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.curso.model.Cargo;
import br.com.curso.model.Departamento;
import br.com.curso.service.CargoService;
import br.com.curso.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	private DepartamentoService dptService;
	private CargoService cargoService;

	@Autowired
	public CargoController(DepartamentoService dptService, CargoService cargoService) {
		this.dptService = dptService;
		this.cargoService = cargoService;
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listarPorPagina(ModelMap model, @RequestParam("page") Optional<Integer> page) {
		
		int paginaAtual = page.orElse(1);
		Page<Cargo> cargos = cargoService.findAll(paginaAtual);
		int totalPaginas = cargos.getTotalPages();
	
		model.addAttribute("cargos", cargos.getContent());
		model.addAttribute("paginaAtual", paginaAtual);
		model.addAttribute("totalPaginas", totalPaginas);
		
		return "/cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.save(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamentos() {
		return dptService.findAll();
	}

	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/cargo/cadastro");
		Optional<Cargo> cargo = cargoService.findById(id);
		mv.addObject("cargo", cargo.get());
		return mv;
	}

	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.update(cargo);
		attr.addFlashAttribute("success", "Cargo atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não removido. Possui funcionários vinculados.");
		} else {
			cargoService.delete(id);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
}
