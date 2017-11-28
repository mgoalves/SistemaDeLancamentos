package com.alves.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.filter.LancamentoFilter;
import com.alves.api.repository.projection.LancamentoProjection;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<LancamentoProjection> projection(LancamentoFilter lancamentoFilter, Pageable pageable);
}
