/**
 * 
 */
package com.concretesolutions.desafio.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.concretesolutions.desafio.api.dto.UserDTO;
import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.UserRepository;
import com.concretesolutions.desafio.api.service.exception.ExistingUserException;
import com.concretesolutions.desafio.api.service.exception.InvalidSessionException;
import com.concretesolutions.desafio.api.service.exception.UserNotFoundException;
import com.concretesolutions.desafio.api.service.exception.UserUnauthorizedException;
import com.concretesolutions.desafio.api.util.TokenUtil;

import static java.util.Objects.requireNonNull;

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

	public User createUser(UserDTO userDTO) {

		try {
			
			User user = new User();
			
			BeanUtils.copyProperties(userDTO, user);
			
			String token = TokenUtil.createJWT(user.getName(), env.getProperty("jwt.issuer"), env.getProperty("jwt.secret"));
			
			user.setToken(this.bCryptPasswordEncoder.encode(token));
			user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

			return this.userRepository.save(user);

		} catch (DataIntegrityViolationException e) {

			throw new ExistingUserException();
		}
	}

	public User login(String email, String password) {

		User userFound = this.userRepository.findByEmail(email);

		if (userFound == null || !bCryptPasswordEncoder.matches(password, userFound.getPassword())) {

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

		if (!token.equals(user.getToken())) {
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
