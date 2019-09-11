package com.blackping.shop.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.bean.testBean;
import com.blackping.shop.service.BlogService;
import com.blackping.shop.util.BlackUtil;

import net.sf.json.JSONObject;

@Controller
public class BlogController {
	
	@Autowired
	BlogService bs;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String blog(Model m, HttpServletRequest req, HttpSession session) {
		if(session.getValueNames().length > 0) {
			Enumeration<String> enumeration = session.getAttributeNames();
			while (enumeration.hasMoreElements()) {
				String string = (String) enumeration.nextElement();
				System.out.println(string);
			}
			System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT").toString());
			
			SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			System.out.println(sc.getAuthentication().getDetails());
		}
		HashMap<String, Object> data = bs.Select(req.getParameter("search"));
		m.addAttribute("data", data);
		return "blog/index";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session) {
		if(session.getAttribute("power") == null) return "redirect:/";
		return "blog/write";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String test(@Valid testBean tb, BindingResult bindresult, HttpSession session) {
		HashMap<String, Object> resultMap;
		HashMap<String, Object> errors = BlackUtil.errors(bindresult);
		if(errors.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("errors", errors);
			resultMap.put("status", false);
		} else {
			
		}
		return "blog/index";
	}

} 
