package com.blog.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

	public LoginResponse(UsernamePasswordAuthenticationToken token) {
		// TODO Auto-generated constructor stub
	}
	private String jwt;
	private String msg;
}
