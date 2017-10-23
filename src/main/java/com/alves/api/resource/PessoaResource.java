package com.alves.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.alves.api.model.Pessoa;
import com.alves.api.repository.PessoaRepository;
import com.alves.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	// Injeções
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;
	// ---------------------------------------------------------------

	@GetMapping
	public List<Pessoa> list() {

		// Retorna da lista de Pessoas
		return pessoaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {

		// Salva no banco e adiciona a variável pessoa.
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	// Buscar pessoa por ID
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> searchByid(@PathVariable Long id) {

		// Buscar do banco
		Pessoa pes = pessoaRepository.findOne(id);

		// Verifica HttpStatus de resposta que pode ser 200 ou 204
		return pes != null ? ResponseEntity.ok(pes) : ResponseEntity.noContent().build();
	}

	// Deletenado pessoa por id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		pessoaRepository.delete(id);
	}

	// Atualizando pessoa completa
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> fullUpdate(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {

		Pessoa pessoaSalva = pessoaService.fullUpdate(id, pessoa);

		return ResponseEntity.ok(pessoaSalva);
	}
}
