package com.blog.service;

import java.util.List;

import com.blog.dto.ApiRespone;
import com.blog.dto.PostDto;
import com.blog.entity.Post;

public interface PostService {
	ApiRespone postNewBlog(PostDto requestDto);
	
	List<PostDto> getAllPosts ();
	
	PostDto getPostById(Long postId);
	
	PostDto updatePost (PostDto postDto , Long postId);
	
	List<PostDto> getPostByCategory (Long categoryId);
	
	ApiRespone deletePostById (Long postId);
	
}
