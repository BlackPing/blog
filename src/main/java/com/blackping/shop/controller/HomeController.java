package com.blackping.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blackping.shop.util.Log;
import com.blackping.shop.util.staticClass;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletRequest res) {
        
		staticClass.Logger.info("test " + staticClass.ip());
		HttpSession session = req.getSession();
		session.setAttribute("test", "세션");
		
		return "template";
	}
	
}
