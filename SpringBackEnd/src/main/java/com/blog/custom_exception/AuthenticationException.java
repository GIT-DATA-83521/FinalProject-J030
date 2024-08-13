package com.blog.custom_exception;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException (String message) {
		super(message);
	}
}
