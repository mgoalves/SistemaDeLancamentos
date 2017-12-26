package com.alves.api.repository.pessoa;

import java.util.List;

import com.alves.api.model.Pessoa;
import com.alves.api.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {

	public List<Pessoa> filter(PessoaFilter pessoaFilter);
	
}
