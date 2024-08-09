package com.blog.service;

import java.util.List;

import com.blog.dto.ApiRespone;
import com.blog.dto.CategoryDto;
import com.blog.dto.CategoryPostDto;
import com.blog.entity.Category;

public interface CategoryService {
	
	CategoryDto addNewCategory (CategoryDto categoryDto);
	
	List<CategoryDto> getAllCategories ();
	
	CategoryDto getCategoryDetails (Long categoryId) ;
	
	CategoryDto updateCategoryDetails (CategoryDto categoryDto , Long categoryId);
	
	ApiRespone deleteCategory (Long categoryId);
	
	CategoryPostDto getCategoryAndPostDetails(Long categoryId);
}
