package com.blackping.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackping.shop.bean.WriteBean;
import com.blackping.shop.service.BlogService;
import com.blackping.shop.service.DataService;
import com.blackping.shop.util.BlackUtil;

import net.sf.json.JSONObject;

@Controller
public class DataController {
	@Autowired
	BlogService bs;
	
	@Autowired
	DataService ds;
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, Model m) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) return "redirect:/";
		HashMap<String, Object> catedata = bs.CateList();
		m.addAttribute("catedata", catedata);
		return "blog/write";
	}
	
	@RequestMapping(value="/write/insert", method=RequestMethod.POST)
	public String writeinsert(HttpSession session, HttpServletRequest req, Model m, @Valid WriteBean wb, BindingResult bindresult,
			@RequestParam(value = "files") MultipartFile[] files, RedirectAttributes ra) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) return "redirect:/";
		
		HashMap<String, Object> resultMap;
		HashMap<String, Object> errors = BlackUtil.errors(bindresult);
		// TODO: Sample errors Map
		// {errors={title=제목을 입력하지 않았습니다.}, status=false}
		/**************************************
		 * Bean Variable Name(key)=MSG
		 * Annotation in the Bean the message
		 **************************************/
		if(errors.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("errors", errors);
			resultMap.put("status", false);
		} else {
			resultMap = ds.Write(wb, files);
		}
		// TODO: Exit data type
		/**************************************
		 * controller fail {errors={title=제목을 입력하지 않았습니다.}, status=false}
		 * service fail { msg="message", status=false}
		 * success {status=true}
		 **************************************/
		ra.addFlashAttribute("error_msg", resultMap);
		return "redirect:/";
	}
	
	@RequestMapping(value="/imageupload", method=RequestMethod.POST)
	public void imageupload(HttpServletRequest req, HttpServletResponse res,HttpSession session, MultipartFile upload) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String realPath = "/var/www/html";
		String SavePath = "/image";
//		String realPath = "C:\\Apache24\\htdocs";
//		String SavePath = "/image";
		String Path = realPath + SavePath;
		
		File file = new File(Path);
		if(!file.exists()) file.mkdirs();
		
		try {
			if(upload.getSize() > 0) {
				String fileName = upload.getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				if(".png".equals(ext) || ".jpg".equals(ext) || ".bmp".equals(ext) || ".gif".equals(ext)) {
					UUID uuid = UUID.randomUUID();
					String url = Path + "/" + uuid + ext;
					FileOutputStream fos = new FileOutputStream(url);
					fos.write(upload.getBytes());
					fos.close();
					Runtime.getRuntime().exec("chmod 757 " + url);
					resultMap.put("uploaded", 1);
					resultMap.put("fileName", fileName + ext);
					// TODO: Image Server Port 9999
					/**********************
					 * http://blackping.shop:9999/image/
					 **********************/
					resultMap.put("url", "http://blackping.shop:9999/image/" + uuid + ext);
				} else {
					HashMap<String, Object> errorMap = new HashMap<String, Object>();
					resultMap.put("uploaded", 0);
					errorMap.put("message", "png, jpg, bmp, gif 파일만 가능합니다.");
					resultMap.put("error", errorMap);
				}
			}
			
			res.getWriter().write(JSONObject.fromObject(resultMap).toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, HttpServletRequest req, Model m) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) return "redirect:/";
		String no = req.getParameter("no");
		HashMap<String, Object> catedata = bs.CateList();
		m.addAttribute("data", ds.SelectBoard(no));
		m.addAttribute("catedata", catedata);
		return "blog/update";
	}
	
	@RequestMapping(value="/renewal", method=RequestMethod.POST)
	public String renewal(HttpSession session, HttpServletRequest req, Model m, @Valid WriteBean wb, BindingResult bindresult,
			@RequestParam(value = "files") MultipartFile[] files, RedirectAttributes ra) {
		HashMap<String, Object> resultMap;
		HashMap<String, Object> errors = BlackUtil.errors(bindresult);

		if(errors.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("errors", errors);
			resultMap.put("status", false);
		} else {
			resultMap = ds.Update(wb, files, req.getParameter("file_no"));
		}

		ra.addFlashAttribute("error_msg", resultMap);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(HttpSession session, HttpServletRequest req, HttpServletResponse res, RedirectAttributes ra) {
		try {
			if(session.getAttribute("SPRING_SECURITY_CONTEXT") == null) res.sendRedirect("/");
			
			String no = req.getParameter("no");
			String category = req.getParameter("category");
			
			ra.addFlashAttribute("data", ds.delete(no));
			res.sendRedirect("/category/" + category);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(HttpServletRequest req, HttpServletResponse res) {
		String fileName = req.getParameter("filename");
		String url = req.getParameter("url");
		
		if(fileName != null && url != null) {
			try {
				InputStream input = new FileInputStream(url);
				OutputStream output = res.getOutputStream();
				IOUtils.copy(input, output);

				res.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8") + "\"");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
