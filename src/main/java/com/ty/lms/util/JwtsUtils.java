package com.ty.lms.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtsUtils {

	@Value("${app.secrete}")
	private String secrate;

	// 6. Method to validate a token!
	public boolean isValidateToken(String token, String username) {
		return getUsername(token).equals(username) && !isTokenExpired(token);
	}

	// 5. Check if the token is expired!
	public boolean isTokenExpired(String token) {
		return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
	}

	// 4. Method to get subject user name!
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	// 3. Method to get expiration date!
	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

	// 2. Method for reading claims!
	@SuppressWarnings("deprecation")
	public Claims getClaims(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(secrate);
		Jws<Claims> claimsJws = parser.parseClaimsJws(token);
		return claimsJws.getBody();
	}

	// 1. Method for token generation!
	public String generateToken(String subject) {
		@SuppressWarnings("deprecation")
		JwtBuilder builder = Jwts.builder().setSubject(subject).setIssuer("HARISHANKAR PATEL")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
				.signWith(SignatureAlgorithm.HS256, secrate);
		return builder.compact();
	}

}
