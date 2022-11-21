package com.myproject.blog.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.blog.payload.ApiResponse;
import com.myproject.blog.payload.UserDto;
import com.myproject.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserContoller {
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(UserContoller.class);
	//POST 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		LOGGER.info(userDto.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(hashedPassword);
		LOGGER.info("hashedPassword : "+hashedPassword);
		LOGGER.info("UserContoller.createUser inside");
		UserDto userCreated = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userCreated, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id)
	{
		return new ResponseEntity<UserDto>(userService.getUserById(id),HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long id)
	{
		LOGGER.info("updateUser method inside");
		return new ResponseEntity<UserDto>(this.userService.updateUser(userDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
		LOGGER.info("deleteUser method inside");
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully.",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		LOGGER.info("getAllUsers method inside");
		return ResponseEntity.ok(this.userService.getAllUser());
	}
}