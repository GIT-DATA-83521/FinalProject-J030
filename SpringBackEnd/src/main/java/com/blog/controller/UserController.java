package com.blog.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.LoginDto;
import com.blog.dto.RegisterDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {
		System.out.println("in Ctor"+getClass());
	}
	
	// Login REST API
	@PostMapping(value = {"/signin"})
	public ResponseEntity<?>loginUser(@RequestBody @Valid LoginDto request){
		System.out.println("in login"+request);
		return ResponseEntity.ok(userService.authenticateUser(request));
		
	}
	
	// Register REST API
	@PostMapping(value = {"/signup"})
	public ResponseEntity<String>registerUser(@RequestBody RegisterDto registerDto){
		
		String response = userService.register(registerDto);
		return new ResponseEntity<> (response , HttpStatus.CREATED);
	}
	
	
}

