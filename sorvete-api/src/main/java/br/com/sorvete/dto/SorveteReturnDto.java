package br.com.sorvete.dto;

import java.util.ArrayList;
import java.util.List;

public class SorveteReturnDto {
	
	private Integer codigo;
	
	private String tipoSorvete;
	
	private List<String> saboreSorvete;
	
	private String data;
	
	 public SorveteReturnDto() {
	        this.saboreSorvete = new ArrayList<>();
	    }

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipoSorvete() {
		return tipoSorvete;
	}

	public void setTipoSorvete(String tipoSorvete) {
		this.tipoSorvete = tipoSorvete;
	}

	public List<String> getSaboreSorvete() {
		return saboreSorvete;
	}

	public void setSaboreSorvete(List<String> saboreSorvete) {
		this.saboreSorvete = saboreSorvete;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
