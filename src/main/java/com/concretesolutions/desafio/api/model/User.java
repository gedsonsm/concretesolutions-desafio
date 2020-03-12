/**
 * 
 */
package com.concretesolutions.desafio.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "user_id")
	private String id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	private LocalDateTime lastLogin;
	
	private String token;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private List<Phone> phones;

	public User()
	{
		created = modified = lastLogin = LocalDateTime.now();
		phones = new ArrayList<>();
	}
}
