package br.com.sorvete.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sorvete.entity.Sabor;
import br.com.sorvete.repository.SaborRepository;

@Service
public class SaborService implements BaseService<Sabor, Long> {
	
	@Autowired
	private SaborRepository saborRepository;

	@Override
	public Sabor save(Sabor e) throws SQLException {
		return saborRepository.save(e);
	}

	@Override
	public Sabor update(Sabor e, Long id) throws SQLException {
		return saborRepository.update(e, id);
	}

	@Override
	public Sabor findById(Long codigo) throws SQLException {
		return saborRepository.findById(codigo);
	}

	@Override
	public void delete(Long codigo) throws SQLException {
		saborRepository.delete(codigo);
	}

	@Override
	public List<Sabor> findAll() throws SQLException {
		return saborRepository.findAll();
	}

}
