package com.blog.controller;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiRespone;
import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {
	
	// DI
	
	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
		System.out.println("In Ctor "+getClass());
	}
	
	// Add Category
	
	@PostMapping("/addcategory")
	public  ResponseEntity<?> addCategory (@RequestBody CategoryDto newCategory){
		System.out.println("In Add Category "+newCategory);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
						.body(categoryService.addNewCategory(newCategory));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new ApiRespone(e.getMessage()));
		}
	}
	
	// Get All Categories
	@GetMapping
	public ResponseEntity<List<Category>> listAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	
	// get Category Details By Id
	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategoryDetails(@PathVariable ("categoryId") @NotNull @Min(value = 1, message = "Category Id must be Greater than Zero") Long categoryId){
		
		System.out.println("in get Category"+categoryId);
		try {
			
			return ResponseEntity.ok(categoryService.getCategoryDetails(categoryId));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								.body (new ApiRespone(e.getMessage()));
		}
	}
	
	// update Category 
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable ("categoryId") Long categoryId , @RequestBody CategoryDto categoryDto){
		System.out.println("In Update Category "+ categoryId + " "+ categoryDto);
		
		return ResponseEntity.ok(categoryService.updateCategoryDetails(categoryDto, categoryId));
	}
	
	// Delete Category by Id
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
		
		System.out.println("In Delete Category"+categoryId);
		return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
	}
	
}
