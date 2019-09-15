package com.blackping.shop.service;

import java.util.HashMap;
import java.util.List;

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
			resultMap.put("files", adi.getData("SL", "blog", "file_all", null).get("result"));
			resultMap.put("status", true);
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> CateList() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap.put("result", adi.getData("SL", "blog", "category", null).get("result"));
			resultMap.put("status", true);
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> CateSelect(String category) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap.put("result", adi.getData("SL", "blog", "category_board", category).get("result"));
			resultMap.put("files", adi.getData("SL", "blog", "file_all", null).get("result"));
			resultMap.put("status", true);
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		
		return resultMap;
	}
	
	public HashMap<String, Object> CateOneSelect(Object category) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			HashMap<String, Object> paramsMap = adi.getData("SL", "blog", "category_one", category);
			List<Object> bufferList;
			HashMap<String, Object> bufferMap;
			resultMap.put("result", paramsMap.get("result"));
			
			bufferList = (List<Object>) paramsMap.get("result");
			bufferMap = (HashMap<String, Object>) bufferList.get(0);
			resultMap.put("files", adi.getData("SL", "blog", "file_select", bufferMap.get("NO").toString()).get("result"));
			resultMap.put("status", true);
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		
		return resultMap;
	}
	
	public void HistoryAdd(HashMap<String, Object> category) {
		try {
			category.put("value", Integer.parseInt(adi.getData("SO", "blog", "historyOne", category).get("result").toString()) + 1);
			adi.getData("UD", "blog", "history", category);
		} catch(DataAccessException e) {
			e.printStackTrace();
		}
	}
}
