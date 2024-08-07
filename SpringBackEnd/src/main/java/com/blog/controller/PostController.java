package com.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiRespone;
import com.blog.dto.PostDto;
import com.blog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	// DI
	@Autowired
	private PostService postService;

	public PostController() {
		System.out.println("In Ctor"+getClass());
	}
	
	@PostMapping
	public ResponseEntity<?> addPost(@RequestBody @Valid PostDto postDto){
		System.out.println("In Add Post "+ postDto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(postService.postNewBlog(postDto));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiRespone(e.getMessage()));
		}
	}
	
}
