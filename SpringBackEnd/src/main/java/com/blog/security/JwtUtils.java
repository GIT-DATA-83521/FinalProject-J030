package com.blog.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	private Key key;
	
	@PostConstruct
	public void init() {
		key =  Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}
	
	public String generateJwtToken(Authentication authentication) {
		log.info("generate jwt token!!" +authentication);
		CustomUserDetails userPrinciple = (CustomUserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrinciple.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
				.claim("authorities", getAuthoritiesString(userPrinciple.getAuthorities()))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}
	
	public Claims validateJwtToken(String jwtToken) {
		
		Claims claims = Jwts.parserBuilder().setSigningKey(key)
							.build()
							.parseClaimsJws(jwtToken)
							.getBody();
		return claims;
	}
	
	private String getAuthoritiesString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream()
											.map(authority -> authority.getAuthority())
											.collect(Collectors.joining(","));
		
		System.out.println(authorityString);
		
		return authorityString;
	}
	
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims){
		String authString = (String) claims.get("authorites");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}

}
