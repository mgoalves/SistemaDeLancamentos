package com.alves.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alves.api.model.Pessoa;
import com.alves.api.repository.PessoaRepository;

/**
 * 
 * @author ALVES
 */

@Service
public class PessoaService {

	// Injeções
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa fullUpdate(Long id, Pessoa pessoa) {

		Pessoa pessoaSalva = searchById(id);
		// Copia propriedades ignorando id
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		// Salva pessoa com os dados atualizados
		pessoaRepository.save(pessoaSalva);

		return pessoaSalva;
	}
	
	//Método que somente troca o Status - busca, troca e salva
	public void statusUpdate(Long id, Boolean ativo) {
		
		Pessoa pessoaSalva = searchById(id);
		pessoaSalva.setAtivo(ativo);		
		pessoaRepository.save(pessoaSalva);
	}

	//Método para buscar por id
	private Pessoa searchById(Long id) {
		
		// Busca do banco dados atuais.
		Pessoa pessoaSalva = pessoaRepository.findOne(id);
		// Faz uma análise para manter dados coerentes e evitar null pointer exception
		if (pessoaSalva == null) {

			// Lança exceção para ser tratada pela HANDLE EXCEPTION
			throw new EmptyResultDataAccessException(1);
		}		
		return pessoaSalva;
	}

}
