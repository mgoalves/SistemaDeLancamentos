package com.alves.api.security.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * Classe filtro de requisições HTTP com o objetivo de identitificar cookies que contenham 
 * um refreshToken e adicionar seu conteúdo no corpo da requição.
 * 
 * @author Alves
 *
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenPreProcessorFilter implements Filter {

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		//Checar se exite o cookie com o refreshToken
		if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equals(req.getParameter("grant_type"))
				&& (req.getCookies() != null)) {
			
			for (Cookie cookies : req.getCookies()) {
			
				if (cookies.getName().equals("refreshToken")) {
					
					String refreshToken = cookies.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
		}
		
		//Continuar as requisões
		chain.doFilter(req, response);
	}
	
	
	

	
	//Classe utilitária para construir um novo HTTPServletRequest
	static class MyServletRequestWrapper extends HttpServletRequestWrapper {
		
		private String refreshToken;

		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			
			ParameterMap<String, String[]> map =  new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] { refreshToken });
			map.setLocked(true);
			
			return map;
		}
	}

	
	
	// Não serão utilizados. --------------------------------------------------
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	@Override
	public void destroy() {
	}
	
	
}
