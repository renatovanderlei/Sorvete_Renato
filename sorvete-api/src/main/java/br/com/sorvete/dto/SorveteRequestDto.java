package br.com.sorvete.dto;

import java.util.List;

import br.com.sorvete.entity.TipoSorvete;

public class SorveteRequestDto {
	
	 private TipoSorvete tipoSorvete;
	 
	 private List<Integer> sabores;

	public TipoSorvete getTipoSorvete() {
		return tipoSorvete;
	}

	public void setTipoSorvete(TipoSorvete tipoSorvete) {
		this.tipoSorvete = tipoSorvete;
	}

	public List<Integer> getSabores() {
		return sabores;
	}

	public void setSabores(List<Integer> sabores) {
		this.sabores = sabores;
	}

}
