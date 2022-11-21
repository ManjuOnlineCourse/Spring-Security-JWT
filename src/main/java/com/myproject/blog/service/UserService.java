package com.myproject.blog.service;

import java.util.List;

import com.myproject.blog.payload.UserDto;

public interface UserService {

	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user, Long id);
	public UserDto getUserById(Long id);
	public List<UserDto> getAllUser();
	public void deleteUser(Long id);
	
}
