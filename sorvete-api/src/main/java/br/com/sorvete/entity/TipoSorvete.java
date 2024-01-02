package br.com.sorvete.entity;

public class TipoSorvete {
	
	private Long id;
	
	private String codigo;
	
	private String tipo;
	
	private Integer qtdBola;
	
	private String peso;
	
	private String descricao;
	
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQtdBola() {
		return qtdBola;
	}

	public void setQtdBola(Integer qtdBola) {
		this.qtdBola = qtdBola;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
