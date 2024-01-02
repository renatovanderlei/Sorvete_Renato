package br.com.sorvete.entity;

import java.time.LocalDate;

public class Sorvete {
	
	private Long id;
	
	private Integer codigo;
	
	private LocalDate data;
	
	private TipoSorvete tipoSorveteId;
	
	private Sabor saborId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TipoSorvete getTipoSorveteId() {
		return tipoSorveteId;
	}

	public void setTipoSorveteId(TipoSorvete tipoSorveteId) {
		this.tipoSorveteId = tipoSorveteId;
	}

	public Sabor getSaborId() {
		return saborId;
	}

	public void setSaborId(Sabor saborId) {
		this.saborId = saborId;
	}

}
