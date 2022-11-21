package com.myproject.blog.service;

import java.util.List;

import com.myproject.blog.payload.PostDto;
import com.myproject.blog.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postdto, Long userId, Long categoryId);
	
	PostDto updatePost(PostDto postdto, Long postId);
	
	void deletePost(Long postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDirection);
	//List<PostDto> getAllPost();
	
	PostDto getPostById(Long postId);
	
	List<PostDto> getPostByCategory(Long categoryId);
	
	List<PostDto> getPostByUser(Long userID);
	
	List<PostDto> searchPosts(String keyword);
	
	
	
	
}
