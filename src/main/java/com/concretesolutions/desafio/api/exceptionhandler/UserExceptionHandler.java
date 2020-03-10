/**
 * 
 */
package com.concretesolutions.desafio.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.concretesolutions.desafio.api.service.exception.CustomException;


/**
 * @author Gedson
 *
 */
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,  HttpStatus status, WebRequest request) {
		
		String mensagem = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		
		List<Erro> listError = Arrays.asList(new Erro(mensagem));
		
		return handleExceptionInternal(ex, listError, headers, status, request) ;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, criaListaErro(ex.getBindingResult()), headers, status, request) ;
	}
	
	@ExceptionHandler({CustomException.class})
	protected ResponseEntity<Object> handleDataIntegrityViolationException(CustomException ex, WebRequest request) {
		
		String messageKey = ex.getErrorCode().getMessageKey();
		String message = this.messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
		
		List<Erro> listError = Arrays.asList(new Erro(message));
		
		return handleExceptionInternal(ex, listError, new HttpHeaders(), HttpStatus.NOT_FOUND, request) ;
	}

	private List<Erro> criaListaErro(BindingResult binding) {
		
		List<Erro> erros = new ArrayList<Erro>();
		String message = null;
		for(FieldError field : binding.getFieldErrors()) {
			
			message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
			erros.add(new Erro(message));
		}
		
		return erros;
	}
	
	public static class Erro {
		
		private String message;
		
		public Erro(String message)  {
			
			super();
			this.message = message;
		}
		
		public String getMensagem()  {
			
			return message;
		}

	}
}
