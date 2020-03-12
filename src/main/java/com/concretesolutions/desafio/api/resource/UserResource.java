/**
 * 
 */
package com.concretesolutions.desafio.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concretesolutions.desafio.api.dto.LoginDTO;
import com.concretesolutions.desafio.api.dto.UserDTO;
import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.service.UserService;

/**
 * Endpoint for login, user creation and user consultation
 * 
 * @author Gedson
 */
@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO user) {
		
		User savedUser = this.userService.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody LoginDTO login) {
		
		User savedUser = this.userService.login(login.getEmail(), login.getPassword());
		
		return ResponseEntity.ok(savedUser);
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<User> userProfile(@RequestHeader(name= "token", required = false) String token, @PathVariable String id) {
		
		User foundUser = this.userService.findValidProfile(id);
		
		this.userService.validateToken(token, foundUser.getToken());
		
		return ResponseEntity.ok(foundUser);
	}
	
}
