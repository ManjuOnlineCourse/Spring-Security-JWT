package com.myproject.blog.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail, String emailbody, String emailSubject) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("manjunandi2016@gmail.com");
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setText(emailbody);
		simpleMailMessage.setSubject(emailSubject);
		
		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail has been sent to "+toEmail);
		
	}
}
