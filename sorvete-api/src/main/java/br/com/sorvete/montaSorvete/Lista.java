package br.com.sorvete.montaSorvete;

import java.util.List;

import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.repository.SorveteRepository;

//define um método sorveteReturnDtoList que espera receber um objeto SorveteRepository e retorna uma lista do tipo SorveteReturnDto
public interface Lista {
	
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository);

}