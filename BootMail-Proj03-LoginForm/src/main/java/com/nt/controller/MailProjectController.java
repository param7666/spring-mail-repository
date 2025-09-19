package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.User;
import com.nt.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MailProjectController {
	
	
	private static String otp;
	
	@Autowired
	private IUserService ser;

	@GetMapping("/")
	public String homepage() {
		System.out.println("MailProjectController.homepage()");
		return "Home";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user) {
		return "Login";
	}
	
	@PostMapping("/login")
	public String Verigylogin(@ModelAttribute("user")User u, HttpSession ses) {
		System.out.println("MailProjectController.Verigylogin()");
		try {
		User logedUser=ser.login(u.getEmail(),u.getPassword());
		if(logedUser!=null) {
		ses.setAttribute("user", logedUser);
		System.out.println(logedUser);
		String otp=ser.sendOtp(u.getEmail());
		MailProjectController.otp=otp;
		return "otp";
		} else return "redirect:/";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		
	}
	
	@PostMapping("/otp")
	public String verifyOtp(HttpServletRequest req,HttpSession ses,RedirectAttributes atrs) throws Exception {
		System.out.println("MailProjectController.verifyOtp()");
		User user=(User)ses.getAttribute("user");
		String entOtp=req.getParameter("opt");
		System.out.println("Mail sended this OTP:: "+MailProjectController.otp);
		System.out.println("User Entered this OTP:: "+entOtp);
		if(entOtp.equals(MailProjectController.otp)) {
			System.out.println("OTP is COrrect");
			return "Dashboard";
		} else {
			System.out.println("Wrong OPT");
			atrs.addFlashAttribute("msg","Wrong OTP");
			return "redirect:/";
		}
	}
}
