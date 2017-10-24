package com.alves.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	//Injeções
	@Autowired
	private LancamentoRepository lancamentoRepository;

	//Retorna lista de lançamentos completos.
	@GetMapping
	public List<Lancamento> list() {
		
		return lancamentoRepository.findAll();
	}
	
	
	//Retorna apenas o lancamento requerido via ID.
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> searchById(@PathVariable Long id) {
		
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		
		return lancamentoSalvo != null ? ResponseEntity.ok(lancamentoSalvo) : ResponseEntity.noContent().build();
	}
}
