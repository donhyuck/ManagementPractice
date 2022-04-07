package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.service.ArticleService;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;

		articleService = new ArticleService(conn);
	}

	public void actionList() throws ServletException, IOException {

		// 페이지 처음
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 페이지 설정과 각 페이지에 해당하는 게시글 목록 가져오기 
		int totalpage = articleService.getForPrintListTotalPage();
		List<Map<String, Object>> articleRows = articleService.getForPrintArticleRows(page);

		request.setAttribute("articleRows", articleRows);
		request.setAttribute("page", page); // 현재 페이지를 알기위해 넘겨준다.
		request.setAttribute("totalpage", totalpage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

}
