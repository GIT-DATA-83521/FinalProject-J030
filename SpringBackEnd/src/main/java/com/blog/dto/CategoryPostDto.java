package com.blog.dto;

import java.util.List;

public class CategoryPostDto extends BaseDto {

	private String categoryName;
	private String description;
	private List<PostResponseDto> posts;
}
