package com.nt.utility;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenereteOtp {

	public String generateOtp() {
		//System.out.println("Helper.generateOtp()");
		 Random rand = new Random();
	        int number = 100000 + rand.nextInt(900000); // ensures it's always 6 digits
	       // System.out.println("Random 6-digit number: " + number);
	        String otp=String.valueOf(number);
	        return otp;
	}
}
