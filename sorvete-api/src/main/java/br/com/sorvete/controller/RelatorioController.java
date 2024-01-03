package br.com.sorvete.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sorvete.dto.RelatorioDto;
import br.com.sorvete.service.RelatorioService;

@RestController
@RequestMapping(value = "/relatorio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class RelatorioController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/sabor")
	public ResponseEntity<List<RelatorioDto>> allSabor(@RequestParam(required = false) String data) {
		
		LocalDate dataNew = LocalDate.parse(data);
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllSabor(dataNew));
	}
	
	@GetMapping("/tipo")
	public ResponseEntity<List<RelatorioDto>> allTipo(@RequestParam(required = false) String data) {
		
		LocalDate dataNew = LocalDate.parse(data);
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllTipo(dataNew));
	}

}
