package com.myproject.blog.service;

import java.util.List;

import com.myproject.blog.payload.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	//get
	CategoryDto getCategory(Long categoryId);
	
	List<CategoryDto> getAllCategory();
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
	//delete
	void deleteCategory(Long categoryId);
}
