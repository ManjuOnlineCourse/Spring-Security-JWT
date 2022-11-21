package com.myproject.blog.payload;

import java.util.Date;

public class PostDto {

	private Long postId;
	private String postTitle;
	private String postImage;
	private String postContent;
	private Date postDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	//private Set<CommentDto> comments=new HashSet<>();
	
	

//	public Set<CommentDto> getComments() {
//		return comments;
//	}
//
//	public void setComments(Set<CommentDto> comments) {
//		this.comments = comments;
//	}

	public PostDto() {
		super();
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	
	
}
