package com.blackping.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value="/login/adminlogin", method=RequestMethod.GET)
	public String adminlogin(HttpSession session) {
		if(session.getAttribute("power") != null) {
			session.invalidate();
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(value="/exception")
	public String denied() {
		return "exception/exception";
	}
}
