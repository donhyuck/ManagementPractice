package com.ldh.java.mp;

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

import com.ldh.java.Util.DBUtil;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글 출력
		response.setContentType("text/html; charset=UTF-8");

		// 접속경로 및 인증
		String url = "jdbc:mysql://localhost:3306/mp?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		String user = "sbsst";
		String password = "sbs123414";

		// Connection 드라이버 활성화
		String driverName = "com.mysql.cj.jdbc.Driver";

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
			conn = DriverManager.getConnection(url, user, password);
			DBUtil dbUtil = new DBUtil(request, response);

			// 원하는 게시글로 이동하기 위해서는 이에 해당하는 번호를 받아야한다.
			int id = Integer.parseInt(request.getParameter("id"));

			// 게시글 상세 보기
			String sql = String.format("SELECT * FROM article WHERE id = %d", id);

			Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);
			request.setAttribute("articleRow", articleRow);

			request.getRequestDispatcher("/jsp/home/detail.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
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
}
