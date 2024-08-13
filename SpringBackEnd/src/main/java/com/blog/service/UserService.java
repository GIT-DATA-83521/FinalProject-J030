package com.blog.service;

import java.util.List;

import com.blog.dto.ApiRespone;
import com.blog.dto.LoginDto;
import com.blog.dto.RegisterDto;
import com.blog.dto.UserResponseDto;
import com.blog.entity.User;

public interface UserService {

	UserResponseDto authenticateUser(LoginDto loginDto);
	
	String register(RegisterDto registerDto);
	
	List<UserResponseDto> getAllUsers();
	
	RegisterDto getUserDetails (Long userId) ;
	
	RegisterDto updateUser(RegisterDto registerDto, Long userId);
	
	ApiRespone deleteUser (Long userId);
	
	
}
