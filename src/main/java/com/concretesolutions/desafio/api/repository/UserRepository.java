/**
 * 
 */
package com.concretesolutions.desafio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concretesolutions.desafio.api.model.User;
import com.concretesolutions.desafio.api.repository.user.UserRepositoryQuery;

/**
 * @author Gedson
 *
 */
public interface UserRepository extends JpaRepository<User, UUID>, UserRepositoryQuery{

	public User findByEmail(String email);
	
}
