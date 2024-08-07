package com.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.custom_exception.ResourceNotFoundException;
import com.blog.dao.CategoryDao;
import com.blog.dto.ApiRespone;
import com.blog.dto.CategoryDto;
import com.blog.entity.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	// DI
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public Category addNewCategory(Category category) {
		
		Category persistentCategory = categoryDao.save(category);
		return persistentCategory;
	}


	@Override
	public List<Category> getAllCategories() {
		
		return categoryDao.findAll();
	}


	@Override
	public CategoryDto getCategoryDetails(Long categoryId) {
		 
		Category categoryEnt = categoryDao.findById(categoryId)
								.orElseThrow(()->
										new ResourceNotFoundException("Invalid Category Id!!!"));
		return mapper.map(categoryEnt, CategoryDto.class);
	}


	@Override
	public CategoryDto updateCategoryDetails(CategoryDto categoryDto, Long categoryId) {
		
		Category category = categoryDao.findById(categoryId)
								.orElseThrow(()-> new ResourceNotFoundException("Invalid Category Id!!"));
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDescription(categoryDto.getDescription());
		
		Category updatedCategory = categoryDao.save(category);
		
		
		return mapper.map(updatedCategory,CategoryDto.class);
	}


	@Override
	public ApiRespone deleteCategory(Long categoryId) {
		
		if(categoryDao.existsById(categoryId)) {
			categoryDao.deleteById(categoryId);
			return new ApiRespone("Category Details Deleted Successfully!!!");
		}
		
		return new ApiRespone("Category Details Deletion Unsuccessful!!!");
		
	}

}
