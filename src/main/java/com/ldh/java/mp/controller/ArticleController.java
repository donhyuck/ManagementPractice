package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void actionList() throws ServletException, IOException {

		// 페이지 처음
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {

			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
		}

		// 페이지 구분
		int itemsInAPage = 15;
		int limitFrom = (page - 1) * itemsInAPage;

		// 페이지에 따라 페이지 갯수 조절
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");

		int totalCount = DBUtil.selectRowIntValue(conn, sql);
		int totalpage = (int) Math.ceil((double) totalCount / itemsInAPage);

		// 게시글 전체 목록 보기
		sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		System.out.println(sql);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		request.setAttribute("articleRows", articleRows);
		request.setAttribute("page", page); // 현재 페이지를 알기위해 넘겨준다.
		request.setAttribute("totalpage", totalpage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

}
