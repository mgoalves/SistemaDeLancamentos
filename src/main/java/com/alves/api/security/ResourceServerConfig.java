package com.alves.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Tipo de validação seja em memória ou banco 
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());					
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/categorias").permitAll() //Acesso sem segurança - exceções
		.anyRequest().authenticated()			//Acesso com segurança
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Não mantém estado de sessão
		.and()
		.csrf().disable();	//Tratamento JS Injection
		
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.stateless(true); //Retorna o estado de sessão
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); //O tipo de encoder p/ senha do usuário
	}
	
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		
		return new OAuth2MethodSecurityExpressionHandler(); //Método que permite adicionar roles ao métodos.
	}
}
