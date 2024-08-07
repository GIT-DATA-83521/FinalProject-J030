package com.blog.dto;



import com.blog.entity.User;

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

	private String commentText;
	private Long commenterId;
	private Long postId;
}
