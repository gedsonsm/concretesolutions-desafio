/**
 * 
 */
package com.concretesolutions.desafio.api.service;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.concretesolutions.desafio.api.dto.UserDTO;
import com.concretesolutions.desafio.api.enums.ErrorCode;
import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.UserRepository;
import com.concretesolutions.desafio.api.service.exception.CustomException;
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

	private final Environment env;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public UserService(Environment env) {
		this.env = requireNonNull(env);
	}

	/**
	 * Method will generate a token for the user, perform the token and password
	 * encryption and persist the user
	 * 
	 * @param Object received for user registration {@link UserDTO}
	 * @return User model saved {@link User}
	 */
	public User createUser(UserDTO userDTO) {

		try {
			User user = new User();

			BeanUtils.copyProperties(userDTO, user);

			String token = TokenUtil.createJWT(user.getName(), env.getProperty("jwt.issuer"),
					env.getProperty("jwt.secret"));

			user.setToken(this.bCryptPasswordEncoder.encode(token));
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

			return this.userRepository.save(user);

		} catch (DataIntegrityViolationException e) {

			throw new CustomException(ErrorCode.EXISTING_EMAIL);
		}
	}

	/**
	 * The Method validates if the email and password are valid for the login, if positive it returns the user's data
	 * 
	 * @param Email of the user who is logging in 
	 * @param Sassword of the user who is performing the login
	 * @return Data of the user who logged in
	 */
	public User login(String email, String password) {

		User userFound = this.userRepository.findByEmail(email);

		if (userFound == null || !this.bCryptPasswordEncoder.matches(password, userFound.getPassword())) {

			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		userFound.setLastLogin(LocalDateTime.now());

		return userFound;
	}

	/**
	 * 
	 * Performs a user search from the id and validates if the login time has been exceeded
	 * 
	 * @param User identifier
	 * @return Data of the user {@link User}
	 */
	public User findValidProfile(String id) {

		User userFound = this.findUserById(id);

		this.validateLoginTime(userFound.getLastLogin());

		return userFound;
	}

	/**
	 * 
	 * Validates whether the token was received and is the same as the user's
	 * 
	 * @param tokenReceived - Token received for validation
	 * @param userToken - Token retrieved from user for validation of what was received
	 */
	public void validateToken(String tokenReceived, String userToken) {

		if (tokenReceived == null || !tokenReceived.equals(userToken)) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
	}

	private User findUserById(String id) {

		Optional<User> userOptional = this.userRepository.findById(id);

		if (!userOptional.isPresent()) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		return userOptional.get();
	}

	private void validateLoginTime(LocalDateTime lastLogin) {

		LocalDateTime nowMinus30 = LocalDateTime.now().minusMinutes(30);

		if (nowMinus30.isAfter(lastLogin)) {
			throw new CustomException(ErrorCode.INVALID_SESSION);
		}
	}

}
