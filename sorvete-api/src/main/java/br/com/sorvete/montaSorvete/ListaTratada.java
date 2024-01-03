package br.com.sorvete.montaSorvete;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.entity.Sorvete;
import br.com.sorvete.repository.SorveteRepository;

@SuppressWarnings("unused")
public class ListaTratada implements Lista {
	
	private SorveteRepository sorveteRepository;
	
	public ListaTratada(SorveteRepository sorveteRepository) {
		this.sorveteRepository = sorveteRepository;
	}
	
	@Override
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository) {
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

}
