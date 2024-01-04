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

//Implemento a interface Lista com um construtor que recebe um SorveteRepository como parâmetro
public class ListaTratada implements Lista {
	
	private SorveteRepository sorveteRepository;
	
	public ListaTratada(SorveteRepository sorveteRepository) {
		this.sorveteRepository = sorveteRepository;
	}
	
	
	 //Vou pegar a lista de todos os sorvetes do repositório, transformar a lista original em uma nova (mapaTransformado), 
	 
	@Override
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository) {
		var sorveteList = sorveteRepository.findAll();
		
		Map<Integer, SorveteReturnDto> mapaTransformado = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		//Eu itero na lista original de sorvetes pegando pelo código
        for (Sorvete sorvete : sorveteList) {
            Integer codigo = sorvete.getCodigo();

            //verifico se o código já está no mapa transformado. Se não estiver, crio um novo SorveteReturnDto 
            if (mapaTransformado.containsKey(codigo)) {
            	SorveteReturnDto sorveteReturnDto = mapaTransformado.get(codigo);
            	sorveteReturnDto.getSaboreSorvete().add(sorvete.getSaborId().getNome());
            } else {
            	SorveteReturnDto sorveteReturnDtoNew = new SorveteReturnDto();
            	sorveteReturnDtoNew.setTipoSorvete(sorvete.getTipoSorveteId().getDescricao());
            	sorveteReturnDtoNew.getSaboreSorvete().add(sorvete.getSaborId().getNome());
            	sorveteReturnDtoNew.setData(sorvete.getData().format(formatter));

            	// Adiciona o novo SorveteReturnDto ao mapa
                mapaTransformado.put(codigo, sorveteReturnDtoNew);
            }
        }
        // Retorna a lista transformada
        return new ArrayList<>(mapaTransformado.values());
	}

}
