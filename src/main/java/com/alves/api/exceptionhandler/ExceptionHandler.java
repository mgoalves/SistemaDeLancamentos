package com.alves.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe responsável pelo tratamento de exceções
 * 
 * Escopo global.
 * 
 * @since 15/10/2017
 * @author Alves
 */

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	// Trata erros quando JSON vierem com paramêtros inválidos.
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDevelop = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Error> errors = Arrays.asList(new Error(msgUser, msgDevelop));

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	

	// Trata os erros que são provenientes de validações inválidas.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = errorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	// Trata exceções para quando realizar um Delete sem valor corresponde no banco
	// Criando tratameto de exceção na mão (não existe sobrescrita de método)
	@org.springframework.web.bind.annotation.ExceptionHandler({ EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, 
															WebRequest request) {
		
		String msgUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDevelop = ex.toString();

		List<Error> errors = Arrays.asList(new Error(msgUser, msgDevelop));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	
	//Trata exceções quando entidades vinculadas não foram encontradas
	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		
		String msgUser = messageSource.getMessage("recurso.operacao-nao-permtida", null, LocaleContextHolder.getLocale());
		
		//Usa uma biblioteca extra para capturar exatamente a causa do erro
		String msgDevelop = ExceptionUtils.getRootCauseMessage(ex);

		List<Error> errors = Arrays.asList(new Error(msgUser, msgDevelop));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	

	// Função que cria lista de erros
	private List<Error> errorList(BindingResult bindingResult) {
		
		List<Error> errors =  new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
		
			String msgUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDevelop = fieldError.toString();
	
			errors.add(new Error(msgUser, msgDevelop));
		}
		return errors;
	}
	
	/**
	 * Classe estática responsável apenas pela construção da mensagem de retorno
	 * para o usuário. Ela precisa de 2 paramêtros - Mensagem para o usuário -
	 * mensagem clara. Mensagem para o desenvolvedor - erro de console.
	 * 
	 * @author Alves
	 */
	public static class Error {

		// Attributes -------------------------
		private String msgUser;
		private String msgDevelop;

		// Constuctor -------------------------
		public Error(String msgUser, String msgDevelop) {
			super();
			this.msgUser = msgUser;
			this.msgDevelop = msgDevelop;
		}

		// Getters and Setters ----------------
		public String getMsgUser() {
			return msgUser;
		}

		public void setMsgUser(String msgUser) {
			this.msgUser = msgUser;
		}

		public String getMsgDevelop() {
			return msgDevelop;
		}

		public void setMsgDevelop(String msgDevelop) {
			this.msgDevelop = msgDevelop;
		}
	}

}
