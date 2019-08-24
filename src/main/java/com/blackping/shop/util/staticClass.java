package com.blackping.shop.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class staticClass {
	public static String ip() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) ip = request.getRemoteAddr();
		return ip;
	}
	public static Log Logger = new Log();
	{
		// 윈도우 Path
//		Logger.Path("C:\\Users\\Admin\\Desktop\\java study\\workspace\\blog\\src\\main\\upload\\log/");
		
		// 리눅스 Path
		Logger.Path("/root/tomcat/webapps/upload/log/");
	}
}
