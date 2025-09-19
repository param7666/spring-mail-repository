package com.nt.service;

public interface IOtpService {

	public String sendOtp(String email) throws Exception ; // generates OTP, stores it, sends via email.

	public boolean verifyOtp(String email, String otp); // checks OTP validity.


}
