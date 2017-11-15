package com.alves.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	// Injeção de gerenciador de conexão
	@PersistenceContext
	private EntityManager manager;

	public List<Lancamento> filter(LancamentoFilter lancamentoFilter) {

		// Inicialização e construção da criteria.
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		// Informar os predicados.
		Predicate[] predicates = doRestrictions(lancamentoFilter, builder, root);
		criteria.where(predicates);

		// Execução da query e retorno dos resultados.
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] doRestrictions(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		//Adiciona restrição para descrição
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			
			predicates.add(
					builder.like(
							builder.lower(root.get("descricao")), 
							"%" + lancamentoFilter.getDescricao().toLowerCase() + "%")
					);
		}
		if (lancamentoFilter.getDataVencimentoDe() != null) {
			
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataVencimento"), 
							lancamentoFilter.getDataVencimentoDe())
					);
		}
		if (lancamentoFilter.getDataVencimentoAte() != null) {
			
			predicates.add(builder.lessThanOrEqualTo(
					root.get("dataVencimento"),
					lancamentoFilter.getDataVencimentoAte())
					);
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
