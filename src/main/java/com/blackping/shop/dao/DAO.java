package com.blackping.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.blackping.shop.controller.HomeController;
import com.blackping.shop.util.Log;

@Repository
public class DAO implements DAOInterface {
	
	@Autowired
	SqlSession session;
	
	@Autowired
	Log Logger;
	
	public void test() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = session.selectOne("test.blog");
		Logger.info(map.toString());
		System.out.println(map.toString());
	}
	
	public HashMap<String, Object> sql(HashMap<String, Object> paramMap) throws DataAccessException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String type = paramMap.get("type").toString();
		
		switch(type) {
		case "select":
			resultMap.put("result", session.selectList("example.select", paramMap));
			break;
		case "selectOne":
			resultMap.put("result", session.selectOne("example.select", paramMap));
			break;
			
		case "insert":
			resultMap.put("result", session.insert("example.insert", paramMap));
			break;
			
		case "update":
		case "delete":
			resultMap.put("result", session.update("example.update", paramMap));
			break;
		}
		
		return resultMap;
	}
}