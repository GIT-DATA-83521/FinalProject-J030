package com.blog.dto;

import java.time.LocalDate;

import com.blog.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDto extends BaseDto {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private LocalDate dob;
	
	private Role role;
	
	private String userCity;
	
	
}
