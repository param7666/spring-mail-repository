package com.nt.service;

import com.nt.model.User;

public interface IUserService {
	
	public User login(String email,String password) throws Exception ;
	
	public String sendOtp(String email) throws Exception;


}
