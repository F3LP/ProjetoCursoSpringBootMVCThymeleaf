package br.com.curso.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.curso.model.Departamento;
import br.com.curso.service.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	private DepartamentoService service;

	@Autowired
	public StringToDepartamentoConverter(DepartamentoService service) {
		this.service = service;
	}

	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}	
		
		Long id = Long.valueOf(text);
		return service.findById(id).get();
	}

}
