package com.ldh.java.mp.dao;

import java.sql.Connection;
import java.util.Map;

import com.ldh.java.mp.dto.Member;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class HomeDao {

	private Connection conn;

	public HomeDao(Connection conn) {
		this.conn = conn;
	}

	public Member getLoginedMember(int loginedMemberId) {

		SecSql sql = SecSql.from("SELECT * FROM `member`");
		sql.append("WHERE id = ?", loginedMemberId);

		Map<String, Object> loginedMemberRow = DBUtil.selectRow(conn, sql);

		Member loginedMember = new Member(loginedMemberRow);

		return loginedMember;
	}
}
