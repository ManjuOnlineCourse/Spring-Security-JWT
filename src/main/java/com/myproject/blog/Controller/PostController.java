package com.myproject.blog.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.blog.AppConstants;
import com.myproject.blog.payload.ApiResponse;
import com.myproject.blog.payload.PostDto;
import com.myproject.blog.payload.PostResponse;
import com.myproject.blog.service.FileService;
import com.myproject.blog.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	public PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Long userId,@PathVariable Long categoryId)
	{
		System.out.println("createPost indside"+postDto.getPostContent());
		PostDto cretedPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(cretedPost, HttpStatus.CREATED);
	} 
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection
			){
		//List<PostDto> allPosts = this.postService.getAllPost(pageNumber,pageSize);
		PostResponse allPosts = this.postService.getAllPost(pageNumber,pageSize, sortBy,sortDirection);
		//System.out.println(pageNumber+" ---- "+pageSize);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	private PostDto getPostById(@PathVariable Long postId) {
		
		return this.postService.getPostById(postId);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	private ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(categoryId),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	private ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId} ")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId){
		
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted succesufuly.", true),HttpStatus.OK);
		
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId),HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	private ResponseEntity<List<PostDto>> postsByKeyword(@PathVariable String keyword) {
		
		List<PostDto> userdto= this.postService.searchPosts(keyword);

		return new ResponseEntity<List<PostDto>>(userdto,HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image, 
			@PathVariable Long postId) 
					throws IOException
	{
		System.out.println("uploadImage method inside");
		PostDto postDto = this.postService.getPostById(postId);
		System.out.println(postDto.getPostId());
		String fileName = this.fileService.uploadImage(path, image);
		System.out.println("fileName form UploadImage Cotroller class : "+ fileName);
		postDto.setPostImage(fileName);
		
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	//http://localhost:9099/api/post/image/be5de2de-e6f3-4016-86d8-92fb28d48a1f.jpg 
	@GetMapping(value="/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	private void downloadImage( @PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
