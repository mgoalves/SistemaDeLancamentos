package com.alves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.api.model.Lancamento;

/**
 * 
 * Interface de auxilio do JPA que já implementa métodos úteis
 * 
 * @author Alves
 *
 */
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
