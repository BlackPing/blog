package com.blackping.shop.dao;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;

public interface DAOInterface {
	public void test() throws Exception;
	public HashMap<String, Object> sql(HashMap<String, Object> paramMap) throws DataAccessException;
}
