package com.blog.service;

import com.blog.dto.LoginDto;
import com.blog.dto.RegisterDto;
import com.blog.dto.UserResponseDto;

public interface UserService {

	UserResponseDto authenticateUser(LoginDto loginDto);
	
	String register(RegisterDto registerDto);
}
