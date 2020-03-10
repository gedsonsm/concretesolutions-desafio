/**
 * 
 */
package com.concretesolutions.desafio.api.service.exception;

import com.concretesolutions.desafio.api.enums.ErrorCode;

/**
 * @author Gedson
 *
 */
public class UserUnauthorizedException extends CustomException {
	
	private static final long serialVersionUID = 1L;

	public UserUnauthorizedException() {
		
		super(ErrorCode.UNAUTHORIZED_USER);
	}
}
