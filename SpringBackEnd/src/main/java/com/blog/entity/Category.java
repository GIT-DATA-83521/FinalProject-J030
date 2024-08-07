package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,exclude = "posts")
public class Category extends BaseEntity {
	
	@Column(name = "cat_name",length = 30,unique = true)
	private String categoryName;
	private String description;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Post> posts = new ArrayList<>();
	
	// helper method to establish Bidire Asso Betn Category and Post
	
	public void addPost(Post post) {
		
		// Category -> Post 
		// Parent -> Child
		this.posts.add(post);
		
		// Post -> Category
		// Child -> Parent
		post.setCategory(this);
	}

	public void removePost(Post post) {
			
			this.posts.remove(post);
			
			post.setCategory(null);
		}
	


}
