package com.blackping.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blackping.shop.bean.WriteBean;
import com.blackping.shop.dao.AutoDAOInterface;

@Service
public class DataService {
	@Autowired
	AutoDAOInterface adi;
	
	public HashMap<String, Object> Write(WriteBean bean, MultipartFile[] files) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int count = Integer.parseInt(adi.getData("SO", "blog", "category_no", bean).get("result").toString()) + 1;
			bean.setCategory_no(Integer.toString(count));
			adi.getData("IS", "blog", "insert", bean);
			resultMap.put("status", true);
			
			try {
//				String realPath = "/root";
//				String SavePath = "/upload";
				String realPath = "C:\\Apache24\\htdocs";
				String SavePath = "/upload";
				String Path = realPath + SavePath;
				
				File file = new File(Path);
				if(!file.exists()) file.mkdirs();
				String fileName;
				String ext;
				
				for(int i = 0; i < files.length; i++) {
					fileName = files[i].getOriginalFilename();
					ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					if(".txt".equals(ext) || ".zip".equals(ext) || ".exe".equals(ext)) {
						UUID uuid = UUID.randomUUID();
						String url = Path + "/" + uuid + ext;
						FileOutputStream fos = new FileOutputStream(url);
						fos.write(files[i].getBytes());
						fos.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
		}
		return resultMap;
	}
}
