package com.nt.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.util.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OtpModel {

	private static  Map<String, String> otpMap;
	private static Map<String, Long> expiryMap;
	@Autowired
	Helper help;
	
	public void storeOtp(String mail,String otp, long expiryTime) {
		//System.out.println("OtpModel.storeOtp()");
		otpMap=new HashMap<>();
		expiryMap=new HashMap<>();
		otpMap.put(mail, otp);
		expiryMap.put(mail, expiryTime);
//		System.out.println(otpMap);
//		System.out.println(expiryMap);
	}
	
	public String getOtp(String email) {
		//System.out.println("OtpModel.getOtp()");
		String otp=otpMap.get(email);
		return otp;
	}
	
}
//void storeOtp(String email, String otp, long expiryTime)
//
//String getOtp(String email)
//
//long getExpiry(String email)
//
//void clearOtp(String email)
