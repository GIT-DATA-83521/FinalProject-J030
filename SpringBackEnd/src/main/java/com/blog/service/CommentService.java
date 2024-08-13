package com.blog.service;

import java.util.List;

import com.blog.dto.CommentDto;

public interface CommentService {

	CommentDto postComment(CommentDto commentDto);
	
	List<CommentDto> getCommentByPostId(long postId);
	
	CommentDto getCommentById(Long postId, Long commentId);
	
	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
	
	void deleteComment(Long postId, Long commentId);
}
