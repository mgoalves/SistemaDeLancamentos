package com.alves.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alves.api.exception.PessoaInativaException;
import com.alves.api.exception.PessoaInexistenteException;
import com.alves.api.model.Lancamento;
import com.alves.api.model.Pessoa;
import com.alves.api.repository.LancamentoRepository;
import com.alves.api.repository.PessoaRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Salva lancamento no banco
	public Lancamento save(Lancamento lancamento) {
		
		// Faz busca da pessoa pelo ID informado
		Pessoa pessoaBusca = pessoaRepository.findOne(lancamento.getPessoa().getId());
		
		//Condições que tratam pessoa nula 
		if(pessoaBusca == null) {
			
			throw new PessoaInexistenteException();
		}
		//Regra de negocio só permite salvar pessoas ativas
		if(pessoaBusca.isInativo()) {
			
			throw new PessoaInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
