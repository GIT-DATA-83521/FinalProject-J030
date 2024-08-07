package com.blog.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiRespone;
import com.blog.dto.CommentDto;
import com.blog.service.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/commentsAPI")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	public CommentController() {
		System.out.println("In Ctor"+getClass());
	}
	
	@PostMapping
	public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto){
		System.out.println("in add comments"+commentDto);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(commentService.postComment(commentDto));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiRespone(e.getMessage()));
		}
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId")Long postId){
		return commentService.getCommentByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
													 @PathVariable(value = "id")Long commentId){
		
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		
		return new ResponseEntity<>(commentDto,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable(value = "postId")Long postId,
													@PathVariable(value = "id") Long commentId){
		
		CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
		
		return new ResponseEntity<>(updatedComment,HttpStatus.OK);
	}
	
	public ResponseEntity<String>deleteComment(@PathVariable(value = "postId")Long postId,
											   @PathVariable(value = "id")Long commentId){
		
		commentService.deleteComment(postId, commentId);
		
		
		return new ResponseEntity<>("Comment deleted!!!",HttpStatus.OK);
	}
	

}
