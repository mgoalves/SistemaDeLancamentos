package com.alves.api.security.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class ApiProperty {
	
	//Atributo para permissões de outros servidores
	final private String originPermitida = "http//localhost:8000";

	public String getOriginPermitida() {
		return originPermitida;
	}


	//Atributo para acessar a classe Security
	private final Security security = new Security();
	
	public Security getSecurity() {
		return security;
	}


	//Agrupando por temas
	public static class Security {
		
		private boolean enableHttps;

		//Getters and Setters
		public boolean isEnableHttps() {
			return enableHttps;
		}
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
	
}
