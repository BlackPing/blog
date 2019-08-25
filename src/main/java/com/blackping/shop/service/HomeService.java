package com.blackping.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackping.shop.dao.DAOInterface;

@Service
public class HomeService {
	@Autowired
	DAOInterface di;
	
	public String test() {
		try {
			di.test();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "test";
	}
}
