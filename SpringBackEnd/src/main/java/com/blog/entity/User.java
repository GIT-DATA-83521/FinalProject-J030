package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
	
	@Column(name = "first_name",length = 30)
	private String firstName;
	
	@Column(name = "last_name",length = 30)
	private String lastName;
	
	@Column(length = 30,unique = true,nullable = false)
	private String email;
	
	@Column(length = 30,nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Role role;
	
	@Lob
	private byte[] image;
	
}
