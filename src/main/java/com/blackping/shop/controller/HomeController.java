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
	
	public static String ip() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) ip = request.getRemoteAddr();
		return ip;
	}
	public static Log Logger = new Log();
	{
		// 윈도우 Path
//		Logger.Path("C:\\Users\\Admin\\Desktop\\java study\\workspace\\blog\\src\\main\\upload\\log/");
		
		// 리눅스 Path
		Logger.Path("/root/tomcat/webapps/upload/log/");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletRequest res) {
        
		HomeController.Logger.info("test " + HomeController.ip());
		HttpSession session = req.getSession();
		session.setAttribute("test", "세션");
		
		return "template";
	}
	
}
