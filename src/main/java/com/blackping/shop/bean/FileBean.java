package com.blackping.shop.bean;

public class FileBean {
	private String url;
	private String filename;
	private String board_no;
	
	public FileBean(String url, String filename, String board_no) {
		this.url = url;
		this.filename = filename;
		this.board_no = board_no;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getBoard_no() {
		return board_no;
	}
	
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
}
