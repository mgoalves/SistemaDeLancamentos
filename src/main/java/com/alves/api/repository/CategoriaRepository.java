package com.alves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
