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
public class UserNotFoundException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		
		super(ErrorCode.USER_NOT_FOUND);
	}

}
