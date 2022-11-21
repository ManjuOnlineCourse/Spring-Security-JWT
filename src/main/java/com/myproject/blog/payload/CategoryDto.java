package com.myproject.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDto {
	
	private Long categoryId;
	
	@NotEmpty
	@Size(max = 40,message = "its should not be more than 40 characters.")
	private String categorytitel;
	
	@NotEmpty
	@Size(min=4,message = "minium 4characters")
	private String categoryDescription;
	
	public CategoryDto() {
		super();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorytitel() {
		return categorytitel;
	}

	public void setCategorytitel(String categorytitel) {
		this.categorytitel = categorytitel;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	
}
