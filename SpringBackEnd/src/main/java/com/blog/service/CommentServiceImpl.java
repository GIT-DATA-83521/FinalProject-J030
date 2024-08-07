package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.custom_exception.ApiException;
import com.blog.custom_exception.BlogAPIException;
import com.blog.custom_exception.ResourceNotFoundException;
import com.blog.dao.CommentDao;
import com.blog.dao.PostDao;
import com.blog.dao.UserDao;
import com.blog.dto.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.entity.Role;
import com.blog.entity.User;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	

	@Override
	public CommentDto postComment(CommentDto commentDto) {
		
		User commenter = userDao.findById(commentDto.getCommenterId())
							.orElseThrow(()-> new ResourceNotFoundException("Invalic commenter id!!!"));
		
		if(commenter.getRole()!= Role.COMMENTER)
			throw new ApiException("Invalid role!!");
		
		Post post = postDao.findById(commentDto.getPostId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid post id!!!"));
		
		if(commenter.getId() == post.getUser().getId())
			throw new ApiException("You can't comment on your own post!!!");
		
		Comment comment = mapper.map(commentDto, Comment.class);
		
		comment.setCommenter(commenter);
		comment.setPost(post);
		
		Comment persistentComment = commentDao.save(comment);
		return mapper.map(persistentComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentByPostId(long postId) {
		
		List<Comment> comments = commentDao.findByPostId(postId);
		
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	private CommentDto mapToDto(Comment comment) {
		
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
		return commentDto;
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		
		Post post = postDao.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid post id!!!"));
				
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid comment id!!!"));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post!!!");
		}
		
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
		
		Post post = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid post id!!!"));
		
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid comment id!!!"));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		
//		comment.setCommenter(commentDto.getCommenterId());
		comment.setCommentBody(commentDto.getCommentText());
		
		Comment updatedComment = commentDao.save(comment);
		
		return mapToDto(updatedComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		
		Post post = postDao.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid postId!!!"));
		
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid comment id!!!"));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		commentDao.delete(comment);
	}

}
