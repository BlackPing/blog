package com.blackping.shop.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class Log {
	private String path;
//	private String path = "C:\\Users\\Admin\\Desktop\\java study\\blog\\src\\main\\upload\\log/";
//	private String path = "C:\\Users\\GD7\\git\\blog\\src\\main\\upload\\log/";
//	private String path = "/root/tomcat/webapps/upload/log/";
	
	public static Log logger = new Log("C:\\Users\\GD7\\git\\blog\\src\\main\\upload\\log/");
	
	public Log() { }
	public Log(String path) {
		this.path = path;
	}
	
	public void Path(String path) {
		this.path = path;
	}

	public void info(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			FileWriter fw = new FileWriter(path + sdf.format(new Date()) + ".txt", true);
			sdf.applyPattern("yyyy-MM-dd-hh:mm:ss");
			fw.write(sdf.format(new Date()) + " - " + str + " \r\n");
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String ip() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null) ip = request.getRemoteAddr();
		return ip;
	}
}
