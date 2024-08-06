package com.blog.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// Post request Dto
public class PostDto extends BaseDto {

	@NotEmpty
	@Size(min = 2 , message = "Post Title should have at least 2 characters. ")
	private String title;
	
	@NotEmpty
	@Size(min = 10 , message = "Post Description should have at least 10 characters. ")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Long authorId;
	
	private Long categoryId;
	
	private Set<CommentDto> comments;
}
