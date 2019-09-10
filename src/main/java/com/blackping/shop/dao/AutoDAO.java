package com.blackping.shop.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class AutoDAO implements AutoDAOInterface {
	@Autowired
	SqlSession sql;
	
	@Override
	public HashMap<String, Object> getData(String type, String namespace, String id, Object bean) throws DataAccessException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		switch(type) {
		case "SL":
			resultMap.put("result", sql.selectList(namespace + "." + id , bean));
			break;
		case "SO":
			resultMap.put("result", sql.selectOne(namespace + "." + id , bean));
			break;
			
		case "IS":
			resultMap.put("result", sql.insert(namespace + "." + id, bean));
			break;
			
		case "UD":
		case "DL":
			resultMap.put("result", sql.update(namespace + "." + id , bean));
			break;
		}
		
		return resultMap;
	}
}
