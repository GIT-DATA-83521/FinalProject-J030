package com.blog.custom_exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogAPIException extends RuntimeException {

	private HttpStatus status;
	
	private String message;

	public BlogAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public BlogAPIException(HttpStatus status, String message ,String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
