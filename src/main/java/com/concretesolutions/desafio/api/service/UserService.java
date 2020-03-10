/**
 * 
 */
package com.concretesolutions.desafio.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.UserRepository;
import com.concretesolutions.desafio.api.service.exception.ExistingUserException;
import com.concretesolutions.desafio.api.service.exception.InvalidSessionException;
import com.concretesolutions.desafio.api.service.exception.UserNotFoundException;
import com.concretesolutions.desafio.api.service.exception.UserUnauthorizedException;
import com.concretesolutions.desafio.api.util.TokenUtil;

/**
 * Service to manipulate user
 * 
 * @author Gedson
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {

		try {
			user.setToken(TokenUtil.createJWT());

			return this.userRepository.save(user);

		} catch (DataIntegrityViolationException e) {

			throw new ExistingUserException();
		}
	}

	public User login(User userReceived) {

		User userFound = this.userRepository.findByEmail(userReceived.getEmail());

		if (userFound == null || !userFound.getPassword().equals(userReceived.getPassword())) {

			throw new UserNotFoundException();
		}

		userFound.setLastLogin(LocalDateTime.now());

		return userFound;
	}

	public User findValidProfile(String token, String id) {

		User userFound = findUserById(id);

		this.validateLoginTime(userFound.getLastLogin());

		return userFound;
	}

	public void validateToken(String token, User user) {
		
		if (token == null) {
			throw new UserUnauthorizedException();
		}

		if (!user.getToken().equals(token)) {
			throw new UserUnauthorizedException();
		}
	}

	private User findUserById(String id) {

		Optional<User> userOptional = this.userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException();
		}

		return userOptional.get();
	}

	private void validateLoginTime(LocalDateTime lastLogin) {

		LocalDateTime nowMinus30 = LocalDateTime.now().minusMinutes(30);

		if (nowMinus30.isAfter(lastLogin)) {
			throw new InvalidSessionException();
		}
	}

}
