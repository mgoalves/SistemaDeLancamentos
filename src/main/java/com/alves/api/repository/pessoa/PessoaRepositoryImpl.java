package com.alves.api.repository.pessoa;

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

import com.alves.api.model.Pessoa;
import com.alves.api.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	// Injeção de gerenciador de conexão
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Pessoa> filter(PessoaFilter pessoaFilter) {

		// Inicialização e construção da criteria.
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		// Informar os predicados.
		Predicate[] predicates = doRestrictions(pessoaFilter, builder, root);
		criteria.where(predicates);

		// Execução da query e retorno dos resultados.
		TypedQuery<Pessoa> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] doRestrictions(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {

		List<Predicate> predicates = new ArrayList<>();

		// Adiciona restrição para descrição
		if (!StringUtils.isEmpty(pessoaFilter.getNome())) {

			predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + pessoaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
