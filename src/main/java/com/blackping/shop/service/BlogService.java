package com.blackping.shop.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.blackping.shop.dao.AutoDAOInterface;

@Service
public class BlogService {
	@Autowired
	AutoDAOInterface adi;

	public HashMap<String, Object> Select(String search) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap.put("result", adi.getData("SL", "blog", "board", search).get("result"));
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> CateList() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap.put("result", adi.getData("SL", "blog", "category", null).get("result"));
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> CateSelect(String category) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap.put("result", adi.getData("SL", "blog", "category_board", category).get("result"));
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
		}
		
		return resultMap;
	}
}
