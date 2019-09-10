package com.blackping.shop.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class testBean {
	
	@NotNull(message="not null")
	String test;
	
	@Size(min=3, max=5, message="길이에러")
	String test2;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getTest2() {
		return test2;
	}
	public void setTest2(String test2) {
		this.test2 = test2;
	}
	
	
}
