package com.blackping.shop.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.util.BlackUtil;

@Controller
public class BlogController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String blog() {
		HashMap<String, Object> node = new HashMap<String, Object>();
		System.out.println(BlackUtil.NPEtoString(node.get("test")));
		return "blog/index";
	}
}
