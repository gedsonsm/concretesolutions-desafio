/**
 * 
 */
package com.concretesolutions.desafio.api.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Gedson
 *
 */
public class TokenUtil {

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
