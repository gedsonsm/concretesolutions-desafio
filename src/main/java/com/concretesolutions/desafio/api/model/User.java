/**
 * 
 */
package com.concretesolutions.desafio.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * User model definition
 * 
 * @author Gedson
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	private LocalDateTime lastLogin;
	
	private String token;

	public User()
	{
		created = modified = lastLogin = LocalDateTime.now();
	}
}
