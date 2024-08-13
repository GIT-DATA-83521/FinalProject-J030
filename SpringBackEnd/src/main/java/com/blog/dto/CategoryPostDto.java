package com.blog.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPostDto extends BaseDto {

	private String categoryName;
	private Long categoryId;
	private String description;
	private List<PostResponseDto> posts;
}
