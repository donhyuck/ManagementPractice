package com.ldh.java.mp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.Config;
import com.ldh.java.mp.exception.SQLErrorException;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글 출력
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Connection 드라이버 활성화
		String driverName = Config.getDBDriverClassName();

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			response.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}

		// Connection / DB연결
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

			// 로그인 상태 확인
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMemberRow = null;

			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				isLogined = true;

				SecSql sql = SecSql.from("SELECT * FROM `member`");
				sql.append("WHERE id = ?", loginedMemberId);

				loginedMemberRow = DBUtil.selectRow(conn, sql);
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberRow", loginedMemberRow);

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

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
