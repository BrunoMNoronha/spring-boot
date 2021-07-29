package com.springboot.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springboot.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secretKey}")
	private String secretKey;
	
	public String getToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date date = new Date();
		Date dataExpiration = new Date(date.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("API Spring Boot")
				.setIssuedAt(date)
				.setSubject(usuario.getId().toString())
				.setExpiration(dataExpiration)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}

}
