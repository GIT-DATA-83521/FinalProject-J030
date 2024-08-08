package com.blog.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.custom_exception.AuthenticationException;
import com.blog.custom_exception.BlogAPIException;
import com.blog.dao.UserDao;
import com.blog.dto.LoginDto;
import com.blog.dto.RegisterDto;
import com.blog.dto.UserResponseDto;
import com.blog.entity.User;

@Service
@org.springframework.transaction.annotation.Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserResponseDto authenticateUser(LoginDto loginDto) {
		User user = userDao.findByEmailAndPassword(loginDto.getEmail(),
													loginDto.getPassword())
				.orElseThrow(()-> new AuthenticationException("Invalid Email or Password!!!!"));
		
		return mapper.map(user,UserResponseDto.class);
	}

	@Override
	public String register(RegisterDto registerDto) {
		
		if(userDao.existsByEmail(registerDto.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists!!!!");
		}
		
			User user = new User();	
			user.setFirstName(registerDto.getFirstName());
			user.setLastName(registerDto.getLastName());
			user.setEmail(registerDto.getEmail());
			user.setPassword(registerDto.getPassword());
			user.setDob(registerDto.getDob());
			user.setRole(registerDto.getRole());
			user.setUserCity(registerDto.getUserCity());
			
			userDao.save(user);
			
		return "User Registered Successfully!!!";
	}

}
