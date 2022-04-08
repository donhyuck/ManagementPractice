package com.ldh.java.mp.dao;

import java.sql.Connection;
import java.util.Map;

import com.ldh.java.mp.dto.Member;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class MemberDao {

	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	// 로그인 아이디 중복체크
	public int isExistLoginId(String loginId) {

		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt ");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		int isExistLoginId = DBUtil.selectRowIntValue(conn, sql);

		return isExistLoginId;
	}

	// 회원가입하기
	public int join(String loginId, String loginPw, String name) {

		SecSql sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate=NOW()");
		sql.append(", `loginId` = ?", loginId);
		sql.append(", `loginPw` = ?", loginPw);
		sql.append(", `name` = ?", name);

		int id = DBUtil.insert(conn, sql);

		return id;
	}

	// 회원 체크
	public Member getMemberByLoginId(String loginId) {

		SecSql sql = SecSql.from("SELECT * FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);

		Member member = new Member(memberRow);

		return member;
	}

}
