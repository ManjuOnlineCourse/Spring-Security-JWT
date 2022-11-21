package com.myproject.blog.payload;

public class CommentDto {
	
	private long commentId;
	private String content;
	
	public CommentDto() {
		super();
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
