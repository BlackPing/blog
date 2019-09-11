package com.blackping.shop.controller;

import javax.servlet.http.HttpServletRequest;
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
	
	@RequestMapping(value="/login/powerinterceptor", method=RequestMethod.POST)
	public String powerInterceptor(HttpSession session, HttpServletRequest req) {
		System.out.println("test");
		String adminCodeCheck = "kingsping!@";
		String adminCode = req.getParameter("adminCode");
		try {
			if(adminCode != null && adminCodeCheck.equals(adminCode)) {
				session.setMaxInactiveInterval(3600);
				session.setAttribute("power", true);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/exception", method= RequestMethod.POST)
	public String denied() {
		return "blog/denied";
	}
}
