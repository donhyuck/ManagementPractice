package com.ldh.java.mp.service;

import java.sql.Connection;

import com.ldh.java.mp.dao.HomeDao;
import com.ldh.java.mp.dto.Member;

public class HomeService {

	private Connection conn;
	private HomeDao homeDao;

	public HomeService(Connection conn) {
		this.conn = conn;
		this.homeDao = new HomeDao(conn);
	}

	public Member getLoginedMember(int loginedMemberId) {
		return homeDao.getLoginedMember(loginedMemberId);
	}
}
