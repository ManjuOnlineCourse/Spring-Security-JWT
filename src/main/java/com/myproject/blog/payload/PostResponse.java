package com.myproject.blog.payload;

import java.util.List;

import com.myproject.blog.Entity.Post;



public class PostResponse {
	
	private List<Post>	content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lasePage;
	
	
	
	public PostResponse() {
		super();
	}
	
	public PostResponse(List<Post> content, int pageNumber, int pageSize, int totalElements, int totalPages,
			boolean lasePage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lasePage = lasePage;
	}
	public List<Post> getContent() {
		return content;
	}
	public void setContent(List<Post> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLasePage() {
		return lasePage;
	}
	public void setLasePage(boolean lasePage) {
		this.lasePage = lasePage;
	}
	
}
