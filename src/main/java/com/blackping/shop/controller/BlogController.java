package com.blackping.shop.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.bean.testBean;
import com.blackping.shop.service.BlogService;
import com.blackping.shop.util.BlackUtil;

@Controller
public class BlogController {
	@Autowired
	BlogService bs;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String blog(Model m, HttpServletRequest req, HttpSession session) {

//			Enumeration<String> enumeration = session.getAttributeNames();
//			while (enumeration.hasMoreElements()) {
//				String string = (String) enumeration.nextElement();
//				System.out.println(string);
//			}
		
		/**************************
		 * session.getAttribute("SPRING_SECURITY_CONTEXT")
		 * getDetails		RemoteIpAddress 접속자 ip, SessionId 세션 id값을 가진 WebAuthenticationDetails 객체 반환
		 * getName			접속 username 반환
		 * getPrincipal		접속자 정보, 권한 가진 userdetails 객체 반환
		 * getAuthorities	사용자가 무슨 권한을 가지고있는지 반환
		 **************************/
//		SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//		if(sc != null) {
//			System.out.println(SecurityUtil.find_UserName(sc));
//			System.out.println(SecurityUtil.find_SessionID(sc));
//			System.out.println(SecurityUtil.find_Ip(sc));
//			System.out.println(SecurityUtil.find_Authorities(sc));
//		}
		
		HashMap<String, Object> data = bs.Select(req.getParameter("search"));
		HashMap<String, Object> catedata = bs.CateList();
		
		m.addAttribute("data", data);
		m.addAttribute("catedata", catedata);
		System.out.println(catedata.toString());
		return "blog/index";
	}
	
	@RequestMapping(value="/category/{key}", method=RequestMethod.GET)
	public String category(@PathVariable String key, HttpServletRequest req, Model m) {
		HashMap<String, Object> data = bs.CateSelect(key);
		HashMap<String, Object> catedata = bs.CateList();
		m.addAttribute("data", data);
		m.addAttribute("catedata", catedata);
		System.out.println(data.toString());
		return "blog/index";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) return "redirect:/";
		return "blog/write";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(HttpSession session) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) return "redirect:/";
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
