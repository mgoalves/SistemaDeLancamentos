package com.alves.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.api.model.Pessoa;
import com.alves.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	//Injeções
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> list() {

		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> save() {
		
	}

	@GetMapping("/{id}")
	public Pessoa searchByid(Long id) {
		
		return pessoaRepository.findOne(id);
	}
}
