package com.blog.service;

import com.blog.dto.ApiRespone;
import com.blog.dto.PostDto;

public interface PostService {
	ApiRespone postNewBlog(PostDto requestDto);
	
	
}
