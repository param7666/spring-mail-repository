package com.nt.runner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.nt.model.OtpModel;
import com.nt.service.IOtpService;

@Component
public class OtpMailProjectTestRunner implements CommandLineRunner {

	
	@Autowired
	private IOtpService ser;


	@Override
	public void run(String... args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Wellcome");
		System.out.println("Enter Your name:: ");
		String name=sc.nextLine();
		System.out.println("Enter Your mail id");
		String email=sc.nextLine();
		String result=ser.sendOtp(email);
		System.out.println(result);
		System.out.println("Check your mail and Enter Otp");
		String otp=sc.nextLine();
		boolean verify=ser.verifyOtp(email, otp);
		if(verify) System.out.println("Login Success ");
		else System.out.println("Login Fail....");
		
		
	}

}
