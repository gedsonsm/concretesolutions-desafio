/**
 * 
 */
package com.concretesolutions.desafio.api.dto;

import java.util.List;

import com.concretesolutions.desafio.api.model.Phone;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO with the necessary information to register a user
 * 
 * @author Gedson
 */
@Getter
@Setter
public class UserDTO {
	
	private String name;
	
	private String email;
	
	private String password;
	
	private List<Phone> phones;

}
