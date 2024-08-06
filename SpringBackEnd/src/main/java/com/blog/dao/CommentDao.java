package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(long postId);
}
