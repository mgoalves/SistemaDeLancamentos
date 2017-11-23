package com.alves.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.alves.api.security.property.ApiProperty;

/**
 * Classe mãe responsável por iniciar a aplicação. 
 * 
 * Manter todas classes em subpacotes de API para que o 
 * spring possa identificar como classe do projeto.
 * 
 * @author ALVES
 *
 */

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SpringbootAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAngularApplication.class, args);
	}
}
