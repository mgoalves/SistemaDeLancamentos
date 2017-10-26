package com.alves.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	//Atributos para pesquisa
	private String descricao;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVencimemtoDe;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataVencimemtoAte;
	
	
	//Getters and Setters
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataVencimemtoDe() {
		return dataVencimemtoDe;
	}
	public void setDataVencimemtoDe(LocalDate dataVencimemtoDe) {
		this.dataVencimemtoDe = dataVencimemtoDe;
	}
	public LocalDate getDataVencimemtoAte() {
		return dataVencimemtoAte;
	}
	public void setDataVencimemtoAte(LocalDate dataVencimemtoAte) {
		this.dataVencimemtoAte = dataVencimemtoAte;
	}
}
