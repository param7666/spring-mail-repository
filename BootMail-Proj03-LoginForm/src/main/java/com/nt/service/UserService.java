package com.nt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.nt.model.User;
import com.nt.repository.IUserRepo;
import com.nt.utility.GenereteOtp;

import jakarta.mail.internet.MimeMessage;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepo repo;
	@Autowired
	private GenereteOtp gen;
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	@Override
	public User login(String email, String password) throws Exception {
		return repo.login(email, password);
	}

	@Override
	public String sendOtp(String email) throws Exception {
		System.out.println("UserService.sendOtp()");
		String mainOtp=gen.generateOtp();
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,false);
		helper.setSentDate(new Date());
		helper.setFrom(from);
		helper.setTo(email);
		helper.setSubject("OTP Mail");
		// set multipart body
		String msg="Your otp is for login "+mainOtp;
		helper.setText(msg);
		//helper.addAttachment("image.jpg", new ClassPathResource("image.jpg"));
		sender.send(message);
		return mainOtp;
	}

}
