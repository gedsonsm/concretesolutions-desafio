package com.concretesolutions.desafio.api.enums;

public enum ErrorCode {
	
	EXISTING_EMAIL("user.existing-email");
	
	private String messageKey;
	
	ErrorCode(String messageKey) {
		this.messageKey = messageKey;
    }
	
	public String getMessageKey() {
		
		return this.messageKey;
	}
	
}
