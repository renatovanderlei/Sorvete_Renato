package br.com.sorvete.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.sorvete.dto.SorveteRequestDto;
import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.entity.Sorvete;
import br.com.sorvete.montaSorvete.Lista;
import br.com.sorvete.montaSorvete.ListaProxy;
import br.com.sorvete.repository.SorveteRepository;
import br.com.sorvete.service.SorveteService;

@RestController
@RequestMapping(value = "/sorvete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class SorveteController {
	
	@Autowired
	private SorveteService sorveteService;
	
	@Autowired
	private SorveteRepository sorveteRepository;
	
	@PostMapping
	public ResponseEntity<Sorvete> save(@RequestBody SorveteRequestDto sorveteDto) {

		sorveteService.validateSorvete(sorveteDto);
		Sorvete sorvete = new Sorvete();
		try {
			sorvete = sorveteService.save(sorveteDto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(sorvete.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(sorvete);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SorveteReturnDto>> all() {
		
		Lista lista = new ListaProxy();
		
		var retorno = ResponseEntity.status(HttpStatus.OK)
				.body(lista.sorveteReturnDtoList(sorveteRepository));
		
		return retorno;
	}

}
