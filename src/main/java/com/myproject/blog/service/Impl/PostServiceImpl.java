package com.myproject.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myproject.blog.Entity.Category;
import com.myproject.blog.Entity.Post;
import com.myproject.blog.Entity.User;
import com.myproject.blog.Repo.CategoryRepo;
import com.myproject.blog.Repo.PostRepo;
import com.myproject.blog.Repo.UserRepo;
import com.myproject.blog.exceptions.ResourceNotFoundException;
import com.myproject.blog.payload.PostDto;
import com.myproject.blog.payload.PostResponse;

import com.myproject.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	private final static Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo ;
	
	@Override
	public PostDto createPost(PostDto postdto,Long userId, Long categoryId) {
		System.out.println("inside the createPost from PostServiceImpl class ");
		System.out.println("categoryId : "+categoryId);
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		Post post = this.modelMapper.map(postdto, Post.class);
		post.setPostImage("defaul.png");
		post.setPostDate(new java.util.Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		post.setPostTitle(postdto.getPostTitle());
		post.setPostContent(postdto.getPostContent());
		post.setPostImage(postdto.getPostImage());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);	
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDirection) {
		
		LOG.info("getAllPost");
		
		Sort sort = sortDirection.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort );
		
		Page<Post> pagePosts = this.postRepo.findAll(pageable);
		
		List<Post> allPosts = pagePosts.getContent();
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(pagePosts.getContent());
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setLasePage(pagePosts.isLast());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		
		//List<Post> allPosts = this.postRepo.findAll();
		//allPosts.forEach(post->System.out.println(post.getPostContent()));
		List<PostDto> postDtos = allPosts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		//List<PostDto> postDtos = allPosts.stream().map(post->this.postToPostDto(post)).collect(Collectors.toList());
		//postDtos.forEach(post->System.out.println("postDtos->"+post.getUser().getId()));
		return postResponse;
	}

	@Override
	public PostDto getPostById(Long postId) {
		LOG.info("getPostById");
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postsByCategory = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsByCategory;
	}

	@Override
	public List<PostDto> getPostByUser(Long userID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(()->new ResourceNotFoundException("User", "userID", userID));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postsByCategory = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsByCategory;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> postsByKeyword=this.postRepo.findByPostTitleContaining(keyword);
		
		List<PostDto> postDto = postsByKeyword.stream().map(postBykeyword->this.modelMapper.map(postBykeyword, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}
	
	public Post dtoToUser(PostDto postDto) {
		Post post=this.modelMapper.map(postDto, Post.class);
		return post;
		
	}
	public PostDto postToPostDto(Post post) {
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
		
	}

}
