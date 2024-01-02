package br.com.sorvete.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sorvete.dto.SorveteRequestDto;
import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.entity.Sorvete;
import br.com.sorvete.entity.TipoSorvete;
import br.com.sorvete.exception.InvalidOperationException;
import br.com.sorvete.repository.SorveteRepository;

@Service
public class SorveteService {
	
	@Autowired
	private TipoSorveteService tipoSorveteService;
	
	@Autowired
	private SaborService saborService; 
	
	@Autowired
	private SorveteRepository sorveteRepository;
	
	public void validateSorvete(SorveteRequestDto sorveteDto) {
		var tipoSorvete = new TipoSorvete();
		
		try {
			tipoSorvete = tipoSorveteService.findById(sorveteDto.getTipoSorvete().getId());
		} catch (SQLException e) {
			throw new InvalidOperationException("Não foi possivel encontrar o ID solicitado.");
		}
		
		if(tipoSorvete.getQtdBola() > sorveteDto.getSabores().size()) {
			throw new InvalidOperationException("Quantidade de Bolas de sorvete maior do que exigido.");
		}
		
	}
	
	public Sorvete save(SorveteRequestDto sorveteDto) throws SQLException {
		
		Sorvete sorvete = new Sorvete();
		Integer lastCodigoSorvete = findCodigoSorvete();
		
		for (Integer sabor : sorveteDto.getSabores()) {
			sorvete.setCodigo(lastCodigoSorvete + 1);
			sorvete.setData(LocalDate.now());
			try {
				sorvete.setSaborId(saborService.findById(Long.valueOf(String.valueOf(sabor))));
			} catch (NumberFormatException | SQLException e) {
				throw new InvalidOperationException("Número informado não encontrado");
			}
			sorvete.setTipoSorveteId(sorveteDto.getTipoSorvete());
			sorveteRepository.save(sorvete);
		}
		
		return sorvete;
	}
	
	public List<SorveteReturnDto> findAll(){
		
		var sorveteList = sorveteRepository.findAll();
		
		Map<Integer, SorveteReturnDto> mapaTransformado = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Sorvete sorvete : sorveteList) {
            Integer codigo = sorvete.getCodigo();

            if (mapaTransformado.containsKey(codigo)) {
            	SorveteReturnDto sorveteReturnDto = mapaTransformado.get(codigo);
            	sorveteReturnDto.getSaboreSorvete().add(sorvete.getSaborId().getNome());
            } else {
            	SorveteReturnDto sorveteReturnDtoNew = new SorveteReturnDto();
            	sorveteReturnDtoNew.setTipoSorvete(sorvete.getTipoSorveteId().getDescricao());
            	sorveteReturnDtoNew.getSaboreSorvete().add(sorvete.getSaborId().getNome());
            	sorveteReturnDtoNew.setData(sorvete.getData().format(formatter));

                mapaTransformado.put(codigo, sorveteReturnDtoNew);
            }
        }

        return new ArrayList<>(mapaTransformado.values());
	}
	
	private Integer findCodigoSorvete() {
		return sorveteRepository.findLastCodigo();
	}
	
}
