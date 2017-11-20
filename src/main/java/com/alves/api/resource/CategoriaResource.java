package com.alves.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.api.model.Categoria;
import com.alves.api.repository.CategoriaRepository;

/**
 * 
 * Classe Controladora para requisições relacionadas a categoria.
 * 
 * Métodos de listar, salvar, buscar por id.
 * 
 * @since 09/10/2017
 * @author ALVES
 *
 */

@Configuration
@RestController
@RequestMapping("/categorias")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CategoriaResource {
	
	
	//Injeções
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Listar categorias
	@GetMapping
	public List<Categoria> list() {
		
		return categoriaRepository.findAll();
	}
	
	//Salvar categoria
	@PostMapping
	public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		
		//Salva no banco e retorna com ID para mostrar para o user
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
							.buildAndExpand(categoriaSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	//Buscar por ID
	@GetMapping({"/{id}"})
	public ResponseEntity<Categoria> searchById(@PathVariable Long id) {
		
		Categoria categoria = categoriaRepository.findOne(id);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.noContent().build();
	}

}
