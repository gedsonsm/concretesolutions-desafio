package com.concretesolutions.desafio.api.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	EXISTING_EMAIL("user.existing-email", HttpStatus.CONFLICT),
	USER_NOT_FOUND("user.not-found", HttpStatus.UNAUTHORIZED),
	UNAUTHORIZED_USER("user.unauthorized", HttpStatus.UNAUTHORIZED),
	INVALID_SESSION("user.invalid-session", HttpStatus.UNAUTHORIZED);
	
	private String messageKey;
	private HttpStatus httpStatus;
	
	ErrorCode(String messageKey, HttpStatus httpStatus) {
		this.messageKey = messageKey;
		this.httpStatus = httpStatus;
    }
	
	public String getMessageKey() {
		
		return this.messageKey;
	}
	
	public HttpStatus getHttpStatus()
	{
		return this.httpStatus;
	}
	
}
