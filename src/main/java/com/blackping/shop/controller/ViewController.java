package com.blackping.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blackping.shop.bean.LoginBean;
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
	public void login(@Valid LoginBean lb, BindingResult bindingResult, HttpServletResponse res, HttpSession session) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> bufferMap = new HashMap<String, Object>();
		if(errors.size() > 0) {
			for(ObjectError error : errors) {
				Object[] err = error.getArguments();
				for(int i = 0; i < err.length; i++) {
					MessageSourceResolvable ms = (MessageSourceResolvable) err[i];
					if("NotNull".equals(error.getCode())) {
						bufferMap.put(ms.getDefaultMessage(), ms.getDefaultMessage() + " input is null");
						resultMap.put("errors", bufferMap.clone());
						resultMap.put("error_code", 101);
						resultMap.put("state", false);
//						bufferMap.clear();
					}
				}
			}
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			HashMap<String, Object> getMap = new HashMap<String, Object>();
			paramMap.put("type", "selectOne");
			paramMap.put("sql", "SELECT COUNT(*) as cnt FROM user WHERE DELYN = 'N' and id = '" + lb.getId() + "'");
			paramMap = di.sql(paramMap);
			
			getMap = (HashMap<String, Object>) paramMap.get("result");
			boolean check = (Integer.parseInt(getMap.get("cnt").toString()) == 1);
			
			if(!check) {
				bufferMap.put("id", "no " + lb.getId() +"");
				resultMap.put("state", false);
				resultMap.put("errors", bufferMap.clone());
				resultMap.put("error_code", 102);
//				bufferMap.clear();
			} else {
				paramMap.put("type", "selectOne");
				paramMap.put("sql", "SELECT COUNT(*) as cnt FROM user WHERE DELYN = 'N' and id = '" + lb.getId() + "' and pw = '" + lb.getPw() + "'");
				paramMap = di.sql(paramMap);
				
				getMap = (HashMap<String, Object>) paramMap.get("result");
				System.out.println(getMap.toString());
				check = (Integer.parseInt(getMap.get("cnt").toString()) == 1);
				System.out.println(check);
				if(check) {
					resultMap.put("state", true);
					session.setAttribute("id", lb.getId());
				}
			}
		}
		
		try {
			res.setContentType("application/json; charset=UTF-8");
			System.out.println(resultMap.toString());
			res.getWriter().write(JSONObject.fromObject(resultMap).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/select", method= RequestMethod.POST)
	public void select(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", "select");
		paramMap.put("sql", "SELECT NO, TXT FROM notice WHERE DELYN = 'N'");
		
		paramMap = di.sql(paramMap);
		
		JSONObject jobj = JSONObject.fromObject(paramMap);
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jobj.toString());
	}
	
	@RequestMapping(value="/insert", method= RequestMethod.POST)
	public void insert(HttpServletRequest req, HttpServletResponse res) throws IOException {
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
