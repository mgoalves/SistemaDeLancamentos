package com.alves.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import com.alves.api.enums.Estados;

@Embeddable
public class Endereco {

	//Atributos ---------------------------------------------
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private Estados estados;
	
	
		
	//Getters and Setters -----------------------------------
	
	@Size(min = 3, max = 50)
	@Column(length = 50)
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	
	@Size(min = 1, max = 5)
	@Column(length = 5)
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Size(min = 3, max = 80)
	@Column(length = 80)
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	@Size(min = 3, max = 50)
	@Column(length = 50)
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	@Size(min = 8, max = 50)
	@Column(length = 8)
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Size(min = 3, max = 50)
	@Column(length = 50)
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Size(min = 2, max = 2)
	@Enumerated(EnumType.STRING)
	public Estados getEstados() {
		return estados;
	}
	public void setEstados(Estados estados) {
		this.estados = estados;
	}
	
	
}
