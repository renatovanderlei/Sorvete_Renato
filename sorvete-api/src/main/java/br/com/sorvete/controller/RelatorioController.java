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
//mapeia as urls que o controlador manipula e os tipos de midia que ele utiliza
@RequestMapping(value = "/relatorio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class RelatorioController {
	
	//Injeta uma instância de RelatorioService
	@Autowired
	private RelatorioService relatorioService;
	
	//mapeio as solicitações do sabor, recebendo um parâmetro opcional "data" e retorna a lista de objetos relacionados ao sabor
	@GetMapping("/sabor")
	public ResponseEntity<List<RelatorioDto>> allSabor(@RequestParam(required = false) String data) {
		
		LocalDate dataNew = LocalDate.parse(data);
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllSabor(dataNew));
	}
	
	//Faz a mesma coisa do anteior, mas com o tipo de sorvete
	@GetMapping("/tipo")
	public ResponseEntity<List<RelatorioDto>> allTipo(@RequestParam(required = false) String data) {
		
		LocalDate dataNew = LocalDate.parse(data);
		return ResponseEntity.status(HttpStatus.OK).body(relatorioService.findAllTipo(dataNew));
	}

}
