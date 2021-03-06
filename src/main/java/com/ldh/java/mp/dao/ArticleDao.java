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
	public Article getArticle(int id) {

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

		Article article = new Article(articleRow);

		return article;
	}

	// 게시글 수정하기
	public void modify(int id, String title, String body) {

		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET `title` = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);

		DBUtil.update(conn, sql);
	}

	// 게시글 삭제하기
	public void delete(int id) {

		SecSql sql = SecSql.from("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(conn, sql);
	}

	// 게시글 등록하기
	public int write(String title, String body, int loginedMemberId) {

		SecSql sql = SecSql.from("INSERT INTO `article`");
		sql.append("SET regDate=NOW()");
		sql.append(", `memberId` = ?", loginedMemberId);
		sql.append(", `title` = ?", title);
		sql.append(", `body` = ?", body);

		int id = DBUtil.insert(conn, sql);

		return id;
	}

	// 작성자 이름 가져오기
	public String getMemberNameByMemberId(int memberId) {

		SecSql sql = SecSql.from("SELECT m.name");
		sql.append("FROM article a");
		sql.append("LEFT JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("WHERE memberId = ?", memberId);

		String memberName = DBUtil.selectRowStringValue(conn, sql);

		return memberName;
	}

	public int getMemberIdById(int id) {

		SecSql sql = SecSql.from("SELECT memberId");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		int memberId = DBUtil.selectRowIntValue(conn, sql);

		return memberId;
	}

}
