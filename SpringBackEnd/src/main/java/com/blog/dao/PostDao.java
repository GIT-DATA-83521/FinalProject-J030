package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Post;

public interface PostDao extends JpaRepository<Post, Long> {

	List<Post> findByCategoryId(long categoryId);
}
