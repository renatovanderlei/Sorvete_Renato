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

	@Autowired // Chamo uma instância do SorveteService
	private SorveteService sorveteService;

	public List<RelatorioDto> findAllSabor(LocalDate data) { // recebe a requisição dos sabores na data escolhida
		var sorveteList = sorveteService.findAllByData(data);

		Map<String, Integer> contagemSabor = new HashMap<>(); // crio um mapa para rastrear a contagem de cada sabor

		for (Sorvete sorvete : sorveteList) {// faço a iteração na lista de sorvetes para contar cada sabor
			String nomeSabor = sorvete.getSaborId().getNome();// puxo o nome do sabor atual
			contagemSabor.put(nomeSabor, contagemSabor.getOrDefault(nomeSabor, 0) + 1);// incremento a contagem para o
																						// sabor no mapa
		}

		List<RelatorioDto> relatorioDtoList = new ArrayList<>(); // lista do RelatorioDTO, que vai ser preenchida com as
																	// contagens de sabores

		for (Map.Entry<String, Integer> entry : contagemSabor.entrySet()) {// iteração com base nas contagens, pegando o
																			// nomeSabor e quantidade
			String nomeSabor = entry.getKey();
			Integer quantidade = entry.getValue();

			RelatorioDto relatorioDto = new RelatorioDto(nomeSabor, quantidade); // crio um objeto com as informações e
																					// adiciono à lista
			relatorioDtoList.add(relatorioDto);
		}

		return relatorioDtoList; // retorno a lista de objetos com as informações de cada sabor

	}

	/*
	 * Para os tipos, eu uso uma variável auxiliar, já que, quando cadastro um tipo
	 * com mais de uma bola, eu repito o código do tipo de acordo com a quantidade
	 * de bolas
	 */
	public List<RelatorioDto> findAllTipo(LocalDate data) {
		var sorveteList = sorveteService.findAllByData(data);
		var relatorioDtoList = new ArrayList<RelatorioDto>();
		var codigo = 0;
		var codigoAux = -1;

		// eu faço a iteração sobre a lista de sorvetes, obtendo o código do sorvete atual e comparando ao do sorvete anterior.
		for (Sorvete sorvete : sorveteList) {
			codigo = sorvete.getCodigo();
			if (codigo != codigoAux) {
				//Se o código do sorvete for diferente, crio um objeto com as info do sorvete e atualizo o código anterior com o atual
				var relatorioDto = new RelatorioDto(sorvete.getTipoSorveteId().getDescricao(), null);
				relatorioDtoList.add(relatorioDto);
				codigoAux = sorvete.getCodigo();
			}
		}

		//Agora, crio um mapa para rastrear o RelatorioDto do tipo de sorvete, verifica quantos tipos de sorvete com o mesmo nome tem, 
		//retorna o nome e a quantidade de cada um e retorna um Arraylist com as informações
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

	//aqui, tenho a função para calcular a a quantidade de nomes iguais
	private int contarQuantidade(List<RelatorioDto> lista, String tipoSorvete) {
		int quantidade = 0;
		  // Itera sobre a lista de RelatorioDto
	    for (RelatorioDto relatorioDto : lista) {
	        // Compara o nome do RelatorioDto com o tipoSorvete fornecido
	        if (tipoSorvete.equals(relatorioDto.getNome())) {
	            // Se os nomes são iguais, incrementa a quantidade
	            quantidade++;
			}
		}
		return quantidade;
	}

}
