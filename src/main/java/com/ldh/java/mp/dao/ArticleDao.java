package com.ldh.java.mp.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class ArticleDao {

	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	// 게시글 전체 갯수
	public int getTotalCount() {

		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");
		int totalCount = DBUtil.selectRowIntValue(conn, sql);

		return totalCount;
	}

	// 게시글 전체 목록 보기
	public List<Map<String, Object>> getArticleRows(int limitFrom, int itemsInAPage) {

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		return articleRows;
	}

}
