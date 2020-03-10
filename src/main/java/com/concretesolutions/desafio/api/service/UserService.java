/**
 * 
 */
package com.concretesolutions.desafio.api.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.UserRepository;
import com.concretesolutions.desafio.api.service.exception.ExistingUserException;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		
		try
		{
			user.setToken(createJWT());
			
			User savedUser = this.userRepository.save(user);
			
			return savedUser;
			
		} catch (DataIntegrityViolationException e) {
			
			throw new ExistingUserException(); 
		}
	}
	
	public static String createJWT() {
		  
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("concretesolutions");
	    Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
	    JwtBuilder builder = Jwts.builder()
	            .setIssuedAt(now)
	            .signWith(signatureAlgorithm, key);
	 
	    return builder.compact();
	}
}
