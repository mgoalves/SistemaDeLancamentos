package com.alves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.api.model.Pessoa;

/**
 * Repositório que implementa metodos para salvar Pessoa
 * 
 * @author ALVES
 *
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
