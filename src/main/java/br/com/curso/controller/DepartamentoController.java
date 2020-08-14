package br.com.curso.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.findAll());
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.save(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("departamento/cadastro");
		Optional<Departamento> departamento = service.findById(id);
		mv.addObject("departamento", departamento.get());
		return mv;
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.update(departamento);
		attr.addFlashAttribute("success", "Departamento atualizado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargos vinculados.");
		} else {
			service.delete(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		return listar(model);
	}
}
