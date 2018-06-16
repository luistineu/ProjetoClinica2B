package com.up.clinica.model;

import java.util.List;

public class TipoAnimal {
	private String acronimo;
	private String nome;
	private String descricao;
	private List<Especie> especies;
	
	public TipoAnimal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoAnimal(String acronimo, String nome, String descricao, List<Especie> especies) {
		super();
		this.acronimo = acronimo;
		this.nome = nome;
		this.descricao = descricao;
		this.especies = especies;
	}
	public String getAcronimo() {
		return acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome_tipoAnimal) {
		this.nome = nome_tipoAnimal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Especie> getEspecies() {
		return especies;
	}
	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}
	
}

