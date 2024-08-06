package com.blog.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Login Auth Request

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {
	
	@NotEmpty(message = "Enter Email")
	@Email(message = "Invalid Email Format")
	private String email;
	
	@NotEmpty(message = "Enter Password")
	private String password;
}
