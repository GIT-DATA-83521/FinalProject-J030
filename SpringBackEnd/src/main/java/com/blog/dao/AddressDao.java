package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
