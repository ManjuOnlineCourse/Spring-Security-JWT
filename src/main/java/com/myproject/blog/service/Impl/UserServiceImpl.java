package com.myproject.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.blog.Entity.User;
import com.myproject.blog.Repo.UserRepo;
import com.myproject.blog.exceptions.ResourceNotFoundException;
import com.myproject.blog.payload.UserDto;

import com.myproject.blog.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		return this.userToUserDto(this.userRepo.save(user));
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		
		//user.setName(userDto.getName());
		//user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		return this.userToUserDto(this.userRepo.save(user));
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDto= users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Long id) {
		User user= this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "ID", id));
		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user=this.modelMapper.map(userDto, User.class);
		return user;
		
	}
	public UserDto userToUserDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
		
	}

}
