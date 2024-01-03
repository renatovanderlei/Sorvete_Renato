package br.com.sorvete.factoryMethod;

import java.sql.SQLException;
import java.util.List;

import br.com.sorvete.entity.Sabor;
import br.com.sorvete.service.SaborService;
import br.com.sorvete.type.PegaLista;

public class ReturnOrderList implements PegaLista {
	
	private SaborService saborService;
	
	public ReturnOrderList(SaborService saborService) {	
		this.saborService = saborService;
	}
	@Override
	public List<Sabor> findAll() {
		try {
			return saborService.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
