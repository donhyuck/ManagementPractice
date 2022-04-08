package com.ldh.java.mp.service;

import java.sql.Connection;

import com.ldh.java.mp.dao.MemberDao;

public class MemberService {

	private Connection conn;
	private MemberDao memberDao;

	public MemberService(Connection conn) {
		this.conn = conn;
		this.memberDao = new MemberDao(conn);
	}

}
