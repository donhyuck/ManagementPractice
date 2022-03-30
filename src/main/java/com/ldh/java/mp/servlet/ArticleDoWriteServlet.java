package com.ldh.java.mp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

@WebServlet("/article/write")
public class ArticleDoWriteServlet extends HttpServlet {
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

			// 입력받은 제목과 내용
			String title = request.getParameter("title");
			String body = request.getParameter("body");

			// 게시글 등록하기
			SecSql sql = SecSql.from("INSERT INTO `article`");
			sql.append("SET regDate=NOW()");
			sql.append(", `title` = ?", title);
			sql.append(", `body` = ?", body);

			int id = DBUtil.insert(conn, sql);
			
			response.getWriter().append(
					String.format("<<script>alert('%d번 글이 등록되었습니다.'); location.replace('list'); </script>", id));


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
