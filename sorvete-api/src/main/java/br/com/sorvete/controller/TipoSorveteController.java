package br.com.sorvete.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import br.com.sorvete.entity.TipoSorvete;
import br.com.sorvete.exception.InvalidOperationException;
import br.com.sorvete.service.TipoSorveteService;

@RestController
@RequestMapping(value = "/tipoSorvete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class TipoSorveteController {
	
	@Autowired
	private TipoSorveteService tipoSorveteService;
	
	@PostMapping
	public ResponseEntity<TipoSorvete> save(@RequestBody TipoSorvete tipoSorvete) {

		try {
			tipoSorveteService.save(tipoSorvete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(tipoSorvete.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(tipoSorvete);
	}
	
	
	@GetMapping
	public ResponseEntity<List<TipoSorvete>> all() {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(tipoSorveteService.findAll());
		} catch (SQLException e) {
			throw new InvalidOperationException("Ocorreu um erro no servidor.");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoSorvete> update(@PathVariable(name = "id", required = true) Long id, @RequestBody(required = true) TipoSorvete tipoSorvete) {
		
		tipoSorvete.setId(id);
		try {
			tipoSorvete = tipoSorveteService.update(tipoSorvete, id);
		} catch (SQLException e) {
			throw new InvalidOperationException("Ocorreu um erro no servidor.");
		}
		return ResponseEntity.ok(tipoSorvete);

	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(name = "id", required = true) Long id) {
		try {
			tipoSorveteService.delete(id);
		} catch (SQLException e) {
			throw new InvalidOperationException("Ocorreu um erro no servidor.");
		}
	}

}
