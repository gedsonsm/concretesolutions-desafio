package com.concretesolutions.desafio.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO with the necessary information for login
 * 
 * @author Gedson
 */
@Getter
@Setter
public class LoginDTO {
	
	private String email;
	
	private String password;

}
