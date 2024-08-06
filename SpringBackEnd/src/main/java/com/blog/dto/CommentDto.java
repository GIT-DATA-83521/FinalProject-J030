package com.blog.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class CommentDto extends BaseDto {

	@NotEmpty(message = "Name should not be empty")
	private String name;
	
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 5 , message = "Comment must be minimum 5 characters")
	private String commentBody;
}
