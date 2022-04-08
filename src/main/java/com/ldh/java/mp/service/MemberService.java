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

	public boolean checkForJoinable(String loginId) {

		if (memberDao.isExistLoginId(loginId) > 0) {
			return false;
		}

		return true;
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

}
