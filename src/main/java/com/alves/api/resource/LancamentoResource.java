package com.alves.api.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alves.api.model.Lancamento;
import com.alves.api.repository.LancamentoRepository;
import com.alves.api.repository.filter.LancamentoFilter;
import com.alves.api.repository.projection.LancamentoProjection;
import com.alves.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	// Injeções
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoService lancamentoService;

	// Retorna lista de lançamentos completos.
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lancamento> searh(LancamentoFilter lancamentoFilter, Pageable pageable) {

		return lancamentoRepository.filter(lancamentoFilter, pageable);
	}

	// Retorna lista de lançamentos completos.
	@GetMapping(params = "projection")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<LancamentoProjection> projection(LancamentoFilter lancamentoFilter, Pageable pageable) {

		return lancamentoRepository.projection(lancamentoFilter, pageable);
	}

	// Salva no banco o lancamento
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento> save(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {

		Lancamento lancamentoSalvo = lancamentoService.save(lancamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(lancamentoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(lancamentoSalvo);
	}

	// Retorna apenas o lancamento requerido via ID.
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Lancamento> searchById(@PathVariable Long id) {

		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);

		return lancamentoSalvo != null ? ResponseEntity.ok(lancamentoSalvo) : ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void deletebyId(@PathVariable Long id) {

		lancamentoRepository.delete(id);
	}
	

	// Atualização de lançamentos.
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento> update(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {

		try {
			
			Lancamento lancamentoSalvo = lancamentoService.update(id, lancamento);
			return ResponseEntity.ok(lancamentoSalvo);
			
		} catch (IllegalArgumentException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
}
