/**
 * 
 */
package com.concretesolutions.desafio.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.UserRepository;

/**
 * Endpoint for login, user creation and user consultation
 * 
 * @author Gedson
 */
@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User savedUser = userRepository.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
}
