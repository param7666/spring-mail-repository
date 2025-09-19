package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseManagementService implements IPurchaseMgmtService{
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromMailId;
	
	
	@Override
	public String shopping(String[] item, Double[] price, String[] toMailIds) throws MessagingException {
		// generate bill
		Double billamt=0.0;
		for(Double p:price) {
			billamt+=p;
		}
		String msg1=Arrays.toString(item)+" are purchased having  prices "+Arrays.toString(price)+" with the bill amount "+billamt;
		
		// triger the mail sender
		String msg2=sendMail(msg1,toMailIds,fromMailId);
		return msg1+" "+msg2;
	}
	
	private String sendMail(String msg, String[]toMailIds ,String fromMailId) throws MessagingException {
		// create and send multiPartmime message
		MimeMessage message=sender.createMimeMessage(); // represent mail message
		MimeMessageHelper helper=new MimeMessageHelper(message, true); // //represents email message allowing the attachments
		
		//seting the header  values
		
		helper.setSentDate(new Date());
		helper.setFrom(fromMailId);
		helper.setTo(toMailIds);
		helper.setSubject("Open it to know it");
		// set multipart body
		helper.setText(msg);
		helper.addAttachment("image.jpg", new ClassPathResource("image.jpg"));
		sender.send(message);
		return "mail message is sent";
		
	}

}
