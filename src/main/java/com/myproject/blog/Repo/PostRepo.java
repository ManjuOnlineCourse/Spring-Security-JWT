package com.myproject.blog.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.blog.Entity.Category;
import com.myproject.blog.Entity.Post;
import com.myproject.blog.Entity.User;



public interface PostRepo extends JpaRepository<Post, Long>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String postTitle);
}
