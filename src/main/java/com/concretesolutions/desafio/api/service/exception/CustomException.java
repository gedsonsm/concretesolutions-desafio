/**
 * 
 */
package com.concretesolutions.desafio.api.service.exception;

import com.concretesolutions.desafio.api.enums.ErrorCode;

/**
 * Exception for existing user
 * 
 * @author Gedson
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	public CustomException(ErrorCode errorCode) {
		
		super();
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		
		return this.errorCode;
	}
}
