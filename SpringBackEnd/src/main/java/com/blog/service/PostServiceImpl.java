package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

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


	// get List of Posts
	
	@Override
	public List<Post> getAllPosts() {
		
		return postDao.findAll();
	}


	// get Post By ID
	@Override
	public PostDto getPostById(Long postId) {
		Post post = postDao.findById(postId)
						.orElseThrow(()->
								new ResourceNotFoundException("Invalid Post Id"));
		return mapToDto(post);
	}


	// Update Post By Id
	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		Post post = postDao.findById(postId)
						.orElseThrow(()-> 
								new ResourceNotFoundException("Invalid Post ID!!!!"));
		
		Category category = categoryDao.findById(postDto.getCategoryId())
							.orElseThrow(()->
									new ResourceNotFoundException("Invalid Category Id!!!"));
		
			post.setTitle(postDto.getTitle());
			post.setDescription(postDto.getDescription());
			post.setContent(postDto.getContent());
			post.setCategory(category);
			Post updatedPost = postDao.save(post);
		
		return mapToDto(updatedPost);
	}



	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		Category category = categoryDao.findById(categoryId)
								.orElseThrow(()->
										new ResourceNotFoundException("Invalid Category Id!!!"));
		List<Post> posts = postDao.findByCategoryId(categoryId);
		
		
		return posts.stream().map((post)->
								mapToDto(post)).collect(Collectors.toList());
	}



	@Override
	public ApiRespone deletePostById(Long postId) {
		if( postDao.existsById(postId)) {
			postDao.deleteById(postId);
			return new ApiRespone("Post Deleted!!!");
			}
		return new ApiRespone("Post Deletion Failed!!");
	}

	// Converting Entity Into DTO
	private PostDto mapToDto (Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
		
		return postDto;
	}
	
}
