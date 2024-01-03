package br.com.sorvete.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sorvete.dto.RelatorioDto;
import br.com.sorvete.entity.Sorvete;

@Service
public class RelatorioService {

	@Autowired
	private SorveteService sorveteService;

	public List<RelatorioDto> findAllSabor(LocalDate data) {
		var sorveteList = sorveteService.findAllByData(data);

		Map<String, Integer> contagemSabor = new HashMap<>();

		for (Sorvete sorvete : sorveteList) {
			String nomeSabor = sorvete.getSaborId().getNome();
			contagemSabor.put(nomeSabor, contagemSabor.getOrDefault(nomeSabor, 0) + 1);
		}

		List<RelatorioDto> relatorioDtoList = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : contagemSabor.entrySet()) {
			String nomeSabor = entry.getKey();
			Integer quantidade = entry.getValue();

			RelatorioDto relatorioDto = new RelatorioDto(nomeSabor, quantidade);
			relatorioDtoList.add(relatorioDto);
		}

		return relatorioDtoList;

	}

	public List<RelatorioDto> findAllTipo(LocalDate data) {
	    var sorveteList = sorveteService.findAllByData(data);
	    var relatorioDtoList = new ArrayList<RelatorioDto>();
	    var codigo = 0;
	    var codigoAux = -1;

	    for (Sorvete sorvete : sorveteList) {
	        codigo = sorvete.getCodigo();
	        if (codigo != codigoAux) {
	            var relatorioDto = new RelatorioDto(sorvete.getTipoSorveteId().getDescricao(), null);
	            relatorioDtoList.add(relatorioDto);
	            codigoAux = sorvete.getCodigo();
	        }
	    }

	    Map<String, RelatorioDto> mapaTiposSorvetes = new HashMap<>();

	    for (RelatorioDto relatorioDto : relatorioDtoList) {
	        if (!mapaTiposSorvetes.containsKey(relatorioDto.getNome())) {
	            int quantidade = contarQuantidade(relatorioDtoList, relatorioDto.getNome());

	            RelatorioDto relaDto = new RelatorioDto(relatorioDto.getNome(), quantidade);
	            mapaTiposSorvetes.put(relatorioDto.getNome(), relaDto);
	        }
	    }

	    return new ArrayList<>(mapaTiposSorvetes.values());

	}
	
	private int contarQuantidade(List<RelatorioDto> lista, String tipoSorvete) {
	    int quantidade = 0;
	    for (RelatorioDto relatorioDto : lista) {
	        if (tipoSorvete.equals(relatorioDto.getNome())) {
	            quantidade++;
	        }
	    }
	    return quantidade;
	}

}
