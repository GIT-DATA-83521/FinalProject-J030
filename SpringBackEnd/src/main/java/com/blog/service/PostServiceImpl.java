package com.blog.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.custom_exception.ResourceNotFoundException;
import com.blog.dao.CategoryDao;
import com.blog.dao.PostDao;
import com.blog.dao.UserDao;
import com.blog.dto.ApiRespone;
import com.blog.dto.PostDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	// dependency Injection
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	
	@Override
	public ApiRespone postNewBlog(PostDto requestDto) {
		// get category from its Id
		
		Category category = categoryDao.findById(requestDto.getCategoryId())
							.orElseThrow(()-> 
								new ResourceNotFoundException("Invalid Category Id!!!"));
		// get user from its Id
		User user = userDao.findById(requestDto.getAuthorId())
							.orElseThrow(()->
									new ResourceNotFoundException("Invalid User Id!!!"));
		
		// mapping DTO To Entity
		Post post = mapper.map(requestDto, Post.class);
		
		//Establishing Bidirectional Associat
		// category 1 <--> * Post 
		category.addPost(post);
		
		// User 1 <-- * Post UniDirectional
		post.setUser(user);
		
		// Saving Post
		Post persistentPost = postDao.save(post);
		
		return new ApiRespone("New Blog Post Added with Id = "+ persistentPost.getId());
	}

}
