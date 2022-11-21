package com.myproject.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.blog.payload.ApiResponse;
import com.myproject.blog.payload.CommentDto;
import com.myproject.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentCotroller {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId,@PathVariable Long userId){
		System.out.println(postId);
		return new ResponseEntity<CommentDto>(this.commentService.createComment(commentDto, postId, userId),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable long commentId){
		this.commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("Comment Deleted succefully",true));
	}
}
