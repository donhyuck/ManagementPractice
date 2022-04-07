package com.ldh.java.mp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ldh.java.mp.dto.Article;
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
	public List<Article> getArticles(int limitFrom, int itemsInAPage) {

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleRow : articleRows) {
			articles.add(new Article(articleRow));
		}

		return articles;
	}

	// 특정 게시글 상세보기
	public Map<String, Object> getArticle(int id) {

		// 게시글 상세 보기
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);
		
		return articleRow;
	}

}
