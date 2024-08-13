package com.blog.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.User;

public interface UserDao extends JpaRepository<User ,Long>{

	Optional<User> findByEmailAndPassword(String email , String pass);
	
	Boolean existsByEmail(String email);
	
}
