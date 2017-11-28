package com.alves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.api.model.Categoria;

/**
 * Repositório que implementa metodos para salvar Categoria
 * 
 * @author ALVES
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
