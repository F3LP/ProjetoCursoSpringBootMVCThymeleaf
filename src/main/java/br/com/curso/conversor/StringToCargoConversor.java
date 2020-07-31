package br.com.curso.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.curso.model.Cargo;
import br.com.curso.service.CargoService;

@Component
public class StringToCargoConversor implements Converter<String, Cargo>{

	private CargoService service;
	
	@Autowired
	public StringToCargoConversor(CargoService service) {
		this.service = service;
	}

	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {			
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id).get();
	}

}
