package com.ldh.java.mp.service;

import java.sql.Connection;

import com.ldh.java.mp.dao.MemberDao;
import com.ldh.java.mp.dto.Member;

public class MemberService {

	private Connection conn;
	private MemberDao memberDao;

	public MemberService(Connection conn) {
		this.conn = conn;
		this.memberDao = new MemberDao(conn);
	}

	public int isLoginIdExist(String loginId) {

		return memberDao.isLoginIdExist(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
