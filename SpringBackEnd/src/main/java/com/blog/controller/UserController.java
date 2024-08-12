package com.blog.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.blog.dto.LoginResponse;
import com.blog.dto.RegisterDto;
import com.blog.dto.UserResponseDto;
import com.blog.entity.User;
import com.blog.security.JwtUtils;
import com.blog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;

	public UserController() {
		System.out.println("in Ctor"+getClass());
	}
	
	// Login REST API
	@PostMapping(value = {"/signin"})
	public ResponseEntity<?>loginUser(@RequestBody @Valid LoginDto request){
		System.out.println("in login"+request);
		//create a token to store un verified user email n password
		UsernamePasswordAuthenticationToken token =
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		
		//invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		
		//=> auth successful !
		System.out.println(verifiedToken.getPrincipal().getClass());
		
		//create JWT n send it to the clnt in response
		LoginResponse response = new LoginResponse(jwtUtils.generateJwtToken(verifiedToken),"Auth success!!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	
	// Register REST API
	@PostMapping(value = {"/signup"})
	public ResponseEntity<String>registerUser(@RequestBody RegisterDto registerDto){
		
		String response = userService.register(registerDto);
		return new ResponseEntity<> (response , HttpStatus.CREATED);
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserResponseDto>> listAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable("userId")@NotNull @Min(value = 1,message = "User id must be greater than zero!")Long userId){
		try {
			
			return ResponseEntity.ok(userService.getUserDetails(userId));
			
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								  .body(new ApiRespone(e.getMessage()));
		}
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId")Long userId, @RequestBody RegisterDto registerDto){
	
		return ResponseEntity.ok(userService.updateUser(registerDto, userId));
	}
	
	@DeleteMapping("deleteUser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
		return ResponseEntity.ok(userService.deleteUser(userId));
	}
	
	
	
}

