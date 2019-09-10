package com.blackping.shop.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.bean.testBean;
import com.blackping.shop.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	BlogService bs;
	
	@RequestMapping(value="/adminlogin", method=RequestMethod.GET)
	public String adminlogin() {
		return "login";
	}
	
	@RequestMapping(value="/powerinterceptor", method=RequestMethod.POST)
	public String powerInterceptor(HttpSession session, HttpServletRequest req) {
		String adminCodeCheck = "kingsping!@";
		String adminCode = req.getParameter("adminCode");
		System.out.println(adminCode);
		
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

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String blog(Model m) {
		HashMap<String, Object> data = bs.Select();
		System.out.println(data.toString());
		m.addAttribute("data", data);
		return "blog/index";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String test(@Valid testBean tb, BindingResult bindresult) {
		System.out.println(tb.getTest());
		System.out.println(tb.getTest2());
		List<ObjectError> errors = bindresult.getAllErrors();
		if(errors.size() > 0) {
			System.out.println(errors.size());
			for(ObjectError error : errors) {
				
				System.out.println(error.getCode());
				System.out.println(error.getObjectName());
				System.out.println(error.getDefaultMessage());
				
				Object[] ttt = error.getArguments();
				DefaultMessageSourceResolvable dms = (DefaultMessageSourceResolvable) ttt[0];
				System.out.println("test1234 "+ dms.getDefaultMessage());
			}
		}
		
		
		return "blog/index";
	}

} 
