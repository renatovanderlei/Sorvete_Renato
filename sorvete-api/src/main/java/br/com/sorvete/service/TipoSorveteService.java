package br.com.sorvete.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sorvete.entity.TipoSorvete;
import br.com.sorvete.repository.TipoSorveteRepository;

@Service
public class TipoSorveteService implements BaseService<TipoSorvete, Long> {
	
	@Autowired
	private TipoSorveteRepository tipoSorveteRepository;

	@Override
	public TipoSorvete save(TipoSorvete e) throws SQLException {
		return tipoSorveteRepository.save(e);
	}

	@Override
	public TipoSorvete update(TipoSorvete e, Long id) throws SQLException {
		return tipoSorveteRepository.update(e, id);
	}

	@Override
	public TipoSorvete findById(Long codigo) throws SQLException {
		return tipoSorveteRepository.findById(codigo);
	}

	@Override
	public void delete(Long codigo) throws SQLException {
		tipoSorveteRepository.delete(codigo);
	}

	@Override
	public List<TipoSorvete> findAll() throws SQLException {
		return tipoSorveteRepository.findAll();
	}

}
