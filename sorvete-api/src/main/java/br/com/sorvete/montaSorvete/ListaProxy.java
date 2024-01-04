package br.com.sorvete.montaSorvete;

import java.util.List;

import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.repository.SorveteRepository;

//implemento a interface para usar uma "lista tratada" sem alterar o código principal dela
public class ListaProxy implements Lista {

	@Override
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository) {
		
		//Aqui, uso um proxy de registro para registrar no console toda vez que a lista for chamada
		System.out.println("Iniciando ciclo de vida de objeto de serviço");
		ListaTratada listaTratada = new ListaTratada(sorveteRepository);
		return listaTratada.sorveteReturnDtoList(sorveteRepository);
	}

}
