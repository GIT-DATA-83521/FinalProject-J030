
package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.blog.dto.PostDto;
import com.blog.entity.Post;
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
	
	// Get All Post
	@GetMapping("/getallposts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		return ResponseEntity.ok(postService.getAllPosts());
	}
	
	
	// Get Post by Id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById (@PathVariable (name = "postId") Long postId){
		return ResponseEntity.ok(postService.getPostById(postId));
	}
	
	// Update Post By Id
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePostById (@Valid @RequestBody PostDto postDto , @PathVariable (name = "postId") Long postId){
		
		PostDto postResponse = postService.updatePost(postDto, postId);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	// Delete Post By Id
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePostById (@PathVariable (name = "postId") Long postId){
		return ResponseEntity.ok(postService.deletePostById(postId));
	}
}
