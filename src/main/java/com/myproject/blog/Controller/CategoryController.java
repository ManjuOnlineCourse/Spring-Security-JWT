package com.myproject.blog.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.blog.payload.ApiResponse;
import com.myproject.blog.payload.CategoryDto;
import com.myproject.blog.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	
		CategoryDto categoryCreated =  this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryCreated,HttpStatus.CREATED);
	}
	

	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId)
	{
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfuly",true),HttpStatus.OK);
	}
	
	
	//getByid
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
		
		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(categoryDto);
		
	}
	
	//get
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategory(){
		
		List<CategoryDto> categoryDto = this.categoryService.getAllCategory();
		return ResponseEntity.ok(categoryDto);
		
	}
	
}
