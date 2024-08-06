package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comments",uniqueConstraints = @UniqueConstraint(columnNames = {"post_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Comment extends BaseEntity {
	private String name;
	private String email;
	
	@Column(name = "comment_body",length = 100)
	private String commentBody;
	
	@JoinColumn(name = "post_id",nullable = false)
	private Post post;
}
