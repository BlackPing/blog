package com.blackping.shop.dao;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;

public interface AutoDAOInterface {
	public HashMap<String, Object> getData(String type, String namespace, String id, Object bean) throws DataAccessException;
}
