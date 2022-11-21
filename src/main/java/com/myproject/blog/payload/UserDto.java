package com.myproject.blog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

	
	private Long id;
	
//	@NotEmpty
//	@Size(min = 4, message = "User name must be minium of 4 charecters")
//	private String name;
	
	@Email(message = "Email is invalid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message = "Password must be min of 3 or max of 10 characters")
	//@Pattern(regexp = "^[a-zA-Z0-9]")
	private String password;
	
//	@NotEmpty
//	private String about;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	public UserDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
