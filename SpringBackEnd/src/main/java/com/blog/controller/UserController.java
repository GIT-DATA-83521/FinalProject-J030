package com.blog.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiRespone;
import com.blog.dto.LoginDto;
import com.blog.dto.RegisterDto;
import com.blog.entity.User;
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
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> listAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable("userId")@NotNull @Min(value = 1,message = "User id must be greater than zero!")Long userId){
		try {
			
			return ResponseEntity.ok(userService.getUserDetails(userId));
			
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								  .body(new ApiRespone(e.getMessage()));
		}
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId")Long userId, @RequestBody RegisterDto registerDto){
	
		return ResponseEntity.ok(userService.updateUser(registerDto, userId));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
		return ResponseEntity.ok(userService.deleteUser(userId));
	}
	
	
	
}

