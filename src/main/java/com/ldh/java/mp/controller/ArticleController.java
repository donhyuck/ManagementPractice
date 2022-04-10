package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.dto.Article;
import com.ldh.java.mp.service.ArticleService;

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

	// 게시글 전체 목록보기
	public void actionList() throws ServletException, IOException {

		// 페이지 처음
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 페이지 설정과 각 페이지에 해당하는 게시글 목록 가져오기
		int totalpage = articleService.getForPrintListTotalPage();
		List<Article> articles = articleService.getForPrintArticles(page);

		request.setAttribute("articles", articles);
		request.setAttribute("page", page); // 현재 페이지를 알기위해 넘겨준다.
		request.setAttribute("totalpage", totalpage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	// 특정 게시글 상세보기
	public void actionDetail() throws ServletException, IOException {

		// 원하는 게시글로 이동하기 위해서는 이에 해당하는 번호를 받아야한다.
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getForPrintArticle(id);

		// 작성자 표시
		int memberId = article.getMemberId();

		request.setAttribute("article", article);
		request.setAttribute("memberId", memberId);
		
		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
	}

	// 수정 페이지 보기
	public void showModifyPage() throws ServletException, IOException {

		// 원하는 게시글로 이동하기 위해서는 이에 해당하는 번호를 받아야한다.
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getForPrintArticle(id);

		request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
	}

	// 게시글 수정하기
	public void actionModify() throws IOException {

		// 입력받은 제목과 내용
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		articleService.modify(id, title, body);

		response.getWriter().append(String
				.format("<<script>alert('%d번 글이 수정되었습니다.'); location.replace('detail?id=%d'); </script>", id, id));
	}

	// 게시글 삭제하기
	public void actionDelete() throws IOException {

		// 원하는 게시글로 이동하기 위해서는 이에 해당하는 번호를 받아야한다.
		int id = Integer.parseInt(request.getParameter("id"));

		articleService.delete(id);
		response.getWriter()
				.append(String.format("<<script>alert('%d번 글이 삭제되었습니다.'); location.replace('list'); </script>", id));

	}

	// 작성 페이지 보기
	public void showWritePage(int loginedMemberId) throws ServletException, IOException {

		// 로그인 확인
		if (loginedMemberId == -1) {
			response.getWriter().append(String
					.format("<<script>alert('로그인 후 이용해주세요.'); location.replace('/MP/menu/member/login'); </script>"));
			return;
		}

		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
	}

	// 게시글 등록하기
	public void actionWrite(int loginedMemberId) throws IOException {

		// 입력받은 제목과 내용
		String title = request.getParameter("title");
		String body = request.getParameter("body");

		int id = articleService.write(title, body, loginedMemberId);

		response.getWriter()
				.append(String.format("<<script>alert('%d번 글이 등록되었습니다.'); location.replace('list'); </script>", id));
	}
}
