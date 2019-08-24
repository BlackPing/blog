package com.blackping.shop.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// ioc 컨테이너에 올리느냐 static 관리를 하느냐
public class Log { // PATH = ""
	private String Path = "";
	
	public Log() { }
	public Log(String Path) {
		this.Path = Path;
	}
	
	public void Path(String Path) {
		this.Path = Path;
	}

	public void info(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			FileWriter fw = new FileWriter(Path + sdf.format(new Date()) + ".txt", true);
			sdf.applyPattern("yyyy-MM-dd-hh:mm:ss");
			fw.write(sdf.format(new Date()) + " - " + str + " \r\n");
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
