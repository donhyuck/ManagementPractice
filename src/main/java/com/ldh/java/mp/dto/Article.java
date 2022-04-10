package com.ldh.java.mp.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {

	private int id;
	private LocalDateTime regDate;
	private String title;
	private String body;
	private int memberId;

	public Article() {

	}

	public Article(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.regDate = (LocalDateTime) row.get("regDate");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.memberId = (int) row.get("memberId");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
