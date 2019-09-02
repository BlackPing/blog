package com.blackping.shop.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.dao.DAOInterface;

import net.sf.json.JSONObject;

@Controller
public class ViewController {
	@Autowired
	DAOInterface di;
	
	@RequestMapping(value="/blog", method= RequestMethod.GET)
	public String blog() {
		return "/blog/index";
	}
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String Main() {
		return "login";
	}
	
	@RequestMapping(value="/board", method= RequestMethod.GET)
	public String board() {
		return "Main";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public void login() {
		System.out.println("test");
	}
	
	@RequestMapping(value="/select", method= RequestMethod.POST)
	public void select(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		System.out.println(req.getParameter("key"));
		paramMap.put("type", "select");
		paramMap.put("sql", "SELECT NO, TXT FROM notice WHERE DELYN = 'N'");
		
		paramMap = di.sql(paramMap);
		
		JSONObject jobj = JSONObject.fromObject(paramMap);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jobj.toString());
	}
	
	@RequestMapping(value="/insert", method= RequestMethod.POST)
	public void insert(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println(req.getParameter("NO"));
		System.out.println(req.getParameter("TXT"));
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", "insert");
		paramMap.put("sql", "INSERT INTO notice (TXT) VALUES ('" + req.getParameter("TXT") + "')");
		
		paramMap = di.sql(paramMap);
		
		JSONObject jobj = JSONObject.fromObject(paramMap);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jobj.toString());
	}
	
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public void update(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", "update");
		paramMap.put("sql", "UPDATE notice SET TXT = '" + req.getParameter("TXT") + "' WHERE NO = " + req.getParameter("NO") + "");
		
		paramMap = di.sql(paramMap);
		
		JSONObject jobj = JSONObject.fromObject(paramMap);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jobj.toString());
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.POST)
	public void delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", "delete");
		paramMap.put("sql", "UPDATE notice SET DELYN = 'Y' WHERE NO = " + req.getParameter("NO") + "");
		
		paramMap = di.sql(paramMap);
		
		JSONObject jobj = JSONObject.fromObject(paramMap);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jobj.toString());
	}
}
