package com.alves.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.LancamentoRepository;
import com.alves.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	//Injeções
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoService lancamentoService;
	

	//Retorna lista de lançamentos completos.
	@GetMapping
	public List<Lancamento> list() {
		
		return lancamentoRepository.findAll();
	}
	
	//Salva no banco o lancamento 
	@PostMapping
	public ResponseEntity<Lancamento> save(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoSalvo = lancamentoService.save(lancamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(lancamentoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(lancamentoSalvo);
	}
	
	//Retorna apenas o lancamento requerido via ID.
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> searchById(@PathVariable Long id) {
		
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		
		return lancamentoSalvo != null ? ResponseEntity.ok(lancamentoSalvo) : ResponseEntity.noContent().build();
	}
}
