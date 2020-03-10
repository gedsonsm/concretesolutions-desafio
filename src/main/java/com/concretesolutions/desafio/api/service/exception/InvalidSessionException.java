/**
 * 
 */
package com.concretesolutions.desafio.api.service.exception;

import com.concretesolutions.desafio.api.enums.ErrorCode;

/**
 * @author Gedson
 *
 */
public class InvalidSessionException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public InvalidSessionException() {
		
		super(ErrorCode.INVALID_SESSION);
	}
}
