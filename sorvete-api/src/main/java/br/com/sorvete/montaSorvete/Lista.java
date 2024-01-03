package br.com.sorvete.montaSorvete;

import java.util.List;

import br.com.sorvete.dto.SorveteReturnDto;
import br.com.sorvete.repository.SorveteRepository;

public interface Lista {
	
	public List<SorveteReturnDto> sorveteReturnDtoList(SorveteRepository sorveteRepository);

}
