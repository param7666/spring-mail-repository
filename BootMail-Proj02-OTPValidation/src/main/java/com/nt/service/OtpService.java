package com.nt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.nt.model.OtpModel;
import com.nt.util.Helper;

import jakarta.mail.internet.MimeMessage;

@Service
public class OtpService implements IOtpService{
	
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private OtpModel mdl;
	@Autowired
	private Helper help;
	
	@Override
	public String sendOtp(String email) throws Exception{
		//System.out.println("OtpService.sendOtp()");
		String mainOtp=help.generateOtp();
		mdl.storeOtp(email, mainOtp, 10000);
		String otp=mdl.getOtp(email);
		String msg="Your Otp is "+otp;
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,false);
		
		helper.setSentDate(new Date());
		helper.setFrom(from);
		helper.setTo(email);
		helper.setSubject("Open it to know it");
		// set multipart body
		helper.setText(msg);
		//helper.addAttachment("image.jpg", new ClassPathResource("image.jpg"));
		sender.send(message);
		return "mail message is sent";
	}

	@Override
	public boolean verifyOtp(String email, String otp) {
		if(mdl.getOtp(email).equalsIgnoreCase(otp)) return true;
		else return false;
	}

}
