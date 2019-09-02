package com.blackping.shop.bean;

import javax.validation.constraints.NotNull;

public class LoginBean {
	@NotNull
	private String id;
	@NotNull
	private String pw;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + "";
	}
}
