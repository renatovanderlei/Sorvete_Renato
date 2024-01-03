package br.com.sorvete.montaSorvete;

import java.util.List;

import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.repository.SorveteRepository;

public class ListaProxy implements Lista {

	@Override
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository) {
		System.out.println("Iniciando ciclo de vida de objeto de serviço");
		ListaTratada listaTratada = new ListaTratada(sorveteRepository);
		return listaTratada.sorveteReturnDtoList(sorveteRepository);
	}

}
