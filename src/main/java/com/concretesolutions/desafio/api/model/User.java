/**
 * 
 */
package com.concretesolutions.desafio.api.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * User model definition
 * 
 * @author Gedson
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private LocalDate created;
	
	private LocalDate modified;
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the created
	 */
	public LocalDate getCreated() {
		return created;
	}
	
	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	
	/**
	 * @return the modified
	 */
	public LocalDate getModified() {
		return modified;
	}
	
	/**
	 * @param modified the modified to set
	 */
	public void setModified(LocalDate modified) {
		this.modified = modified;
	}
	
}
