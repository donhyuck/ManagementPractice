package com.ldh.java.mp.service;

import java.sql.Connection;
import java.util.List;

import com.ldh.java.mp.dao.ArticleDao;
import com.ldh.java.mp.dto.Article;

public class ArticleService {

	private Connection conn;
	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.conn = conn;
		this.articleDao = new ArticleDao(conn);
	}

	public int getItemsInAPage() {
		return 15;
	}

	public int getForPrintListTotalPage() {

		// 페이지 구분
		int itemsInAPage = getItemsInAPage();

		// 페이지에 따라 페이지 갯수 조절
		int totalCount = articleDao.getTotalCount();
		int totalpage = (int) Math.ceil((double) totalCount / itemsInAPage);

		return totalpage;
	}

	public List<Article> getForPrintArticles(int page) {

		// 페이지 구분
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articles = articleDao.getArticles(limitFrom, itemsInAPage);

		return articles;
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getArticle(id);
	}

	public void modify(int id, String title, String body) {
		articleDao.modify(id, title, body);
	}

	public void delete(int id) {
		articleDao.delete(id);
	}

	public int write(String title, String body, int loginedMemberId) {
		return articleDao.write(title, body, loginedMemberId);
	}

	public String getMemberNameByMemberId(int memberId) {
		return articleDao.getMemberNameByMemberId(memberId);
	}

	public int getMemberIdById(int id) {
		return articleDao.getMemberIdById(id);
	}
}
