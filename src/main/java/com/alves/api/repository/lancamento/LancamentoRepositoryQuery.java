package com.alves.api.repository.lancamento;

import java.util.List;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filter(LancamentoFilter lancamentoFilter);
	
}
