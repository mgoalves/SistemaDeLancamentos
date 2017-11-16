package com.alves.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Tipo de validação seja em memória ou banco 
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin") //Autenticação
		.roles("ROLE"); 					 //Autorização
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/categorias").permitAll() //Acesso sem segurança - exceções
		.anyRequest().authenticated()			//Acesso com segurança
		.and()
		.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Não mantém estado de sessão
		.and()
		.csrf().disable();	//Tratamento JS Injection
		
	}
	
}
