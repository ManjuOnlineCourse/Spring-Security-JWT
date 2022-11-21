package com.myproject.blog.service;

import java.util.List;

import com.myproject.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Long postId, Long userId);
	List<CommentDto> getAllComments();
	CommentDto getCommentById(long commentId);
	CommentDto updateComment();
	void deleteComment(long commentId);
	
}
