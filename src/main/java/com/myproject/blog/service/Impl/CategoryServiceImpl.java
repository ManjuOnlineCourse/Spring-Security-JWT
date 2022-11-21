package com.myproject.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.blog.Entity.Category;
import com.myproject.blog.Repo.CategoryRepo;
import com.myproject.blog.exceptions.ResourceNotFoundException;
import com.myproject.blog.payload.CategoryDto;
import com.myproject.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final static Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		LOG.info("createCategory");
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return  modelMapper.map(addedCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Long categoryId) {
		LOG.info("getCategory");
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		LOG.info("getAllCategory");
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDto = categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategorytitel(categoryDto.getCategorytitel());
		
		Category updatedCategory = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long categoryId) {

		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		this.categoryRepo.delete(category);
	}

}
