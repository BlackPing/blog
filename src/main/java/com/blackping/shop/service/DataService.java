package com.blackping.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blackping.shop.bean.FileBean;
import com.blackping.shop.bean.WriteBean;
import com.blackping.shop.dao.AutoDAOInterface;

@Service
public class DataService {
	@Autowired
	AutoDAOInterface adi;
	
	String realPath = "/root";
	String SavePath = "/upload";
//	String realPath = "C:\\Apache24\\htdocs";
//	String SavePath = "/upload";
	
	public HashMap<String, Object> Write(WriteBean bean, MultipartFile[] files) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int count = Integer.parseInt(adi.getData("SO", "blog", "category_no", bean).get("result").toString()) + 1;
			bean.setCategory_no(Integer.toString(count));
			adi.getData("IS", "blog", "insert", bean);
			resultMap.put("status", true);
			
			try {
				String Path = realPath + SavePath;
				
				File file = new File(Path);
				if(!file.exists()) file.mkdirs();
				String fileName;
				String ext;
				
				int fileSize = 0;
				if(files[0].getSize() > 0) {
					fileSize = files.length;
				}
				
				for(int i = 0; i < fileSize; i++) {
					fileName = files[i].getOriginalFilename();
					ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					if(".txt".equals(ext) || ".zip".equals(ext) || ".exe".equals(ext)) {
						UUID uuid = UUID.randomUUID();
						String url = Path + "/" + uuid + ext;
						FileOutputStream fos = new FileOutputStream(url);
						fos.write(files[i].getBytes());
						fos.close();
						
						url = URLEncoder.encode(url);
						adi.getData("IS", "blog", "file_insert", new FileBean(url, fileName, adi.getData("SO", "blog", "board_count", null).get("result").toString()));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> delete(String no) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			adi.getData("UD", "blog", "board_delete", no);
			resultMap.put("status", true);
		} catch(DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> SelectBoard(String no) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("result", adi.getData("SO", "blog", "board_no", no).get("result"));
			resultMap.put("status", true);
		} catch (DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> Update(WriteBean bean, MultipartFile[] files, String file_no) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			adi.getData("UD", "blog", "update", bean);
			resultMap.put("status", true);
			try {
				String Path = realPath + SavePath;
				
				File file = new File(Path);
				if(!file.exists()) file.mkdirs();
				String fileName;
				String ext;
				
				List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>) adi.getData("SL", "blog", "file_select", file_no).get("result");
				for(int i = 0; i < fileList.size(); i++) {
					File delfile = new File(URLEncoder.encode(fileList.get(i).get("URL").toString(), "UTF-8"));
					if(file.exists()) file.delete();
				}
				adi.getData("KILL", "blog", "file_delete", file_no);
				
				int fileSize = 0;
				if(files[0].getSize() > 0) {
					fileSize = files.length;
				}
				
				for(int i = 0; i < fileSize; i++) {
					fileName = files[i].getOriginalFilename();
					ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					if(".txt".equals(ext) || ".zip".equals(ext) || ".exe".equals(ext)) {
						UUID uuid = UUID.randomUUID();
						String url = Path + "/" + uuid + ext;
						FileOutputStream fos = new FileOutputStream(url);
						fos.write(files[i].getBytes());
						fos.close();
						
						url = URLEncoder.encode(url);
						adi.getData("IS", "blog", "file_insert", new FileBean(url, fileName, file_no));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			resultMap.put("msg", "네트워크 에러");
			resultMap.put("status", false);
		}
		return resultMap;
	}
}
