package com.example.api.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationService {

	static final long EXPIRATION_TIME = 600000;
	static final String SECRET = "Secret";

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String AUTHORITIES_KEY = "authorities";

	static void addAuthentication(HttpServletResponse res, Authentication auth) {
		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		String token = Jwts.builder().setSubject(auth.getName()).claim(AUTHORITIES_KEY, authorities)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + " " + token);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION_HEADER);
		if (token != null) {

			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();

			Collection<? extends GrantedAuthority> authorities = Arrays
					.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			String user = claims.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
		}
		return null;
	}

}
