package br.com.sorvete.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sorvete.dto.SorveteRequestDto;
import br.com.sorvete.entity.Sorvete;
import br.com.sorvete.entity.TipoSorvete;
import br.com.sorvete.exception.InvalidOperationException;
import br.com.sorvete.repository.SorveteRepository;

@Service
public class SorveteService{
	
	@Autowired
	private TipoSorveteService tipoSorveteService;
	
	@Autowired
	private SaborService saborService; 
	
	@Autowired
	private SorveteRepository sorveteRepository;
	
	// Primeiro, faço a validação do sorvete
	public void validateSorvete(SorveteRequestDto sorveteDto) {
		var tipoSorvete = new TipoSorvete();
		
		try {
			tipoSorvete = tipoSorveteService.findById(sorveteDto.getTipoSorvete().getId());
		} catch (SQLException e) {
			throw new InvalidOperationException("Não foi possivel encontrar o ID solicitado.");
		}
		
		if(tipoSorvete.getQtdBola() > sorveteDto.getSabores().size()) {
			throw new InvalidOperationException("Quantidade de Bolas de sorvete maior do que exigido.");
		}
		
	}
	
	//E vou criar meu sorvete
	public Sorvete save(SorveteRequestDto sorveteDto) throws SQLException {
		
		Sorvete sorvete = new Sorvete();
		Integer lastCodigoSorvete = findCodigoSorvete(); //pego o código do sorvete atual
		
	//uso o DTO para requisitar os ids dos sabores, incrementar o último código do sorvete, pegar a data, e o id do tipo de sorvete
		for (Integer sabor : sorveteDto.getSabores()) {
			sorvete.setCodigo(lastCodigoSorvete + 1);
			sorvete.setData(LocalDate.now());
			try {
				sorvete.setSaborId(saborService.findById(Long.valueOf(String.valueOf(sabor))));
			} catch (NumberFormatException | SQLException e) {
				throw new InvalidOperationException("Número informado não encontrado");
			}
			sorvete.setTipoSorveteId(sorveteDto.getTipoSorvete());
			sorveteRepository.save(sorvete);
		}
		
		return sorvete;
	}
	
	public List<Sorvete> findAllByData(LocalDate data){
		return sorveteRepository.findAllByData(data);
	}
	
	private Integer findCodigoSorvete() {
		return sorveteRepository.findLastCodigo();
	}
}