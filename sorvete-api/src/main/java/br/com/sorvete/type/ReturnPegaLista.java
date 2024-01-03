package br.com.sorvete.type;

import br.com.sorvete.factoryMethod.ReturnOrderList;
import br.com.sorvete.service.SaborService;

public class ReturnPegaLista {
	
	public static PegaLista listaOrdenada(SaborService saborService) {
			return new ReturnOrderList(saborService);
	}

}
