package br.com.sorvete.dto;

public class RelatorioDto {
	
	private String nome;
	
	private Integer quantidade;

	public RelatorioDto(String nomeSabor, Integer quantidade2) {
		this.nome = nomeSabor;
		this.quantidade = quantidade2;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
