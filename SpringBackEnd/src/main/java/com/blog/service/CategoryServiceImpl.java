package com.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.custom_exception.ApiException;
import com.blog.custom_exception.ResourceNotFoundException;
import com.blog.dao.CategoryDao;
import com.blog.dao.UserDao;
import com.blog.dto.ApiRespone;
import com.blog.dto.CategoryDto;
import com.blog.dto.CategoryPostDto;
import com.blog.entity.Category;
import com.blog.entity.Role;
import com.blog.entity.User;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	// DI
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CategoryDto addNewCategory(CategoryDto categoryDto) {
		
//		User user = userDao.findById(categoryDto.getAdminId())
//				.orElseThrow(() -> new ResourceNotFoundException("Not an admin!!"));
//		
//		if(user.getRole()==Role.ADMIN)
//			throw new ApiException("Invalid role!!");
		
		// Assuming you have these classes and interfaces in place
		// import statements

		    // Convert DTO to Entity
		
		
		
		    Category category = new Category();
		  //  category.setCategoryName(null);

		    // Set additional fields (if needed)
		    category.setCategoryName(categoryDto.getCategoryName());
		    category.setDescription(categoryDto.getDescription());

		    // Save the entity to the database
		    Category persistentCategory = categoryDao.save(category);

		    // Convert the saved entity back to DTO and return
		    return mapper.map(persistentCategory, CategoryDto.class);
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


	@Override
	public CategoryPostDto getCategoryAndPostDetails(Long categoryId) {
		
		Category categoryEnt = categoryDao
								.getCategoryAndPosts(categoryId)
								.orElseThrow(()->new ResourceNotFoundException("Invalid Category ID!!"));
		return mapper.map(categoryEnt, CategoryPostDto.class);
	}
	
	

}
