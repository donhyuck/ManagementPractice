package com.ldh.java.mp.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class ArticleService {

	private Connection conn;

	public ArticleService(Connection conn) {
		this.conn = conn;
	}

	public int getItemsInAPage() {
		return 15;
	}

	public int getForPrintListTotalPage() {

		// 페이지 구분
		int itemsInAPage = getItemsInAPage();

		// 페이지에 따라 페이지 갯수 조절
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");

		int totalCount = DBUtil.selectRowIntValue(conn, sql);
		int totalpage = (int) Math.ceil((double) totalCount / itemsInAPage);

		return totalpage;
	}

	public List<Map<String, Object>> getForPrintArticleRows(int page) {

		// 페이지 구분
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;

		// 게시글 전체 목록 보기
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		return articleRows;
	}

}
