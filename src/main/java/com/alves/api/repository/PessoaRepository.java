package com.alves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.api.model.Pessoa;
import com.alves.api.repository.pessoa.PessoaRepositoryQuery;

/**
 * Repositório que descreve metodos para salvar Pessoa
 * 
 * @author ALVES
 *
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

}
