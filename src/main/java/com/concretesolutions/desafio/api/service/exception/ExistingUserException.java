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
public class ExistingUserException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public ExistingUserException() {
		
		super(ErrorCode.EXISTING_EMAIL);
	}

}
