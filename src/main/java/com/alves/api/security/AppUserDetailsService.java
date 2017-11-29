package com.alves.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alves.api.model.Usuario;
import com.alves.api.repository.UsuarioRepository;
import com.alves.api.securityutil.UsuarioSistema;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioOptinal = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptinal.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}


	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		usuario.getPermissoes().forEach(
						p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase()))
					);
		
		return authorities;
	}

}
