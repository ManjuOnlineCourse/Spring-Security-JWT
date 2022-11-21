package com.myproject.blog.service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.blog.Entity.Comment;
import com.myproject.blog.Entity.Post;
import com.myproject.blog.Entity.User;
import com.myproject.blog.Repo.CommetRepo;
import com.myproject.blog.Repo.PostRepo;
import com.myproject.blog.Repo.UserRepo;
import com.myproject.blog.exceptions.ResourceNotFoundException;
import com.myproject.blog.payload.CommentDto;

import com.myproject.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommetRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper mapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment newComment = this.commentRepo.save(comment);
		return this.mapper.map(newComment, CommentDto.class);
	}
	
	@Override
	public void deleteComment(long commentId) {
		
		Comment commentForDelete=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		
		this.commentRepo.delete(commentForDelete);
		
	} 

	@Override
	public List<CommentDto> getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto getCommentById(long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto updateComment() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
