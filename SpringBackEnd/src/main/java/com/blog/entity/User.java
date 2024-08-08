package com.blog.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
	
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Role role;
	
	@Lob
	private byte[] image;
	
	@Column(name = "city",length = 30)
	private String userCity;
	
}
