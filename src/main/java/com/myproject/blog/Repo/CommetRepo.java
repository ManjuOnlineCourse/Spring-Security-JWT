package com.myproject.blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.blog.Entity.Comment;



public interface CommetRepo extends JpaRepository<Comment, Long>{

}
