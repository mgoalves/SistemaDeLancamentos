package com.alves.api.securityutil;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.alves.api.model.Usuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	
	//Constructor
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> permissoes) {

		super(usuario.getEmail(), usuario.getSenha(), permissoes);
		this.usuario = usuario;
	}

	
	//Getters and Setters
	public Usuario getUsuario() {
		return usuario;
	}
}
