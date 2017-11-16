package com.alves.api.repository.lancamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.filter.LancamentoFilter;

@Repository
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	//Injeção de gerenciador de conexão
	@PersistenceContext
	private EntityManager manager;
	
	public List<Lancamento> filter(LancamentoFilter lancamentoFilter){
		
		//Inicialização e construção da criteria
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		return null;
	}
}
