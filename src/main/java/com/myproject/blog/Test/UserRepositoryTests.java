package com.myproject.blog.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRepositoryTests {
 

     public static void main(String[] args) throws Exception {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String password = passwordEncoder.encode("{bcrypt}Manju@123");
         System.out.println(password);
	}
}
