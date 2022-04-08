package com.ldh.java.mp.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {

	public int id;
	public LocalDateTime regDate;
	public String loginId;
	public String loginPw;
	public String name;

	public Member() {

	}

	public Member(Map<String, Object> row) {
		this.id = (int) row.get("id");
		this.regDate = (LocalDateTime) row.get("regDate");
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
