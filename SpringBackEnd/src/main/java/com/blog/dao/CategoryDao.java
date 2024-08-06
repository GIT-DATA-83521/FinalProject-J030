package com.blog.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
	// custom query- to get category + post details 
	
	@Query("select c from Category c left join fetch c.posts where c.id=:id")
	Optional<Category> getCategoryAndPosts(Long id);

}
