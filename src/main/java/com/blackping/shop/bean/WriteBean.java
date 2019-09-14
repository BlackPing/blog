package com.blackping.shop.bean;

import javax.validation.constraints.NotNull;

public class WriteBean {
	@NotNull(message="제목을 입력하지 않았습니다.")
	private String title;
	
	@NotNull(message="카테고리를 선택하지 않았습니다.")
	private String category_name;
	
	private String text;
	private String category_no;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory_no() {
		return category_no;
	}

	public void setCategory_no(String category_no) {
		this.category_no = category_no;
	}

	@Override
	public String toString() {
		return "WriteBean [title=" + title + ", category_name=" + category_name + ", text=" + text + ", category_no="
				+ category_no + "]";
	}
}
