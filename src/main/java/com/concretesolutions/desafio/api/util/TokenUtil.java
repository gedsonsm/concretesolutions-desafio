/**
 * 
 */
package com.concretesolutions.desafio.api.util;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.core.env.Environment;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.util.Objects.requireNonNull;

/**
 * @author Gedson
 *
 */
public class TokenUtil {
	
	public static String createJWT(String name, String issuer, String privateKey) {
		  
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(privateKey);
	    Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
	    JwtBuilder builder = Jwts.builder()
	    		.setId(UUID.randomUUID().toString())
	    		.setSubject(name)
	    		.setIssuer(issuer)
	            .setIssuedAt(now)
	            .signWith(signatureAlgorithm, key);
	 
	    return builder.compact();
	}
	
}
