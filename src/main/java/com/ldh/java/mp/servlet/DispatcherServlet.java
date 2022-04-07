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
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.Config;
import com.ldh.java.mp.controller.ArticleController;
import com.ldh.java.mp.controller.MemberController;
import com.ldh.java.mp.exception.SQLErrorException;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

@WebServlet("/menu/*")
public class DispatcherServlet extends HttpServlet {

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

			// 모든 요청 이전에 무조건 해줘야 하는 일
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

			// URI
			// http://localhost:8082/MP/menu/[]/[]
			String requestUri = request.getRequestURI();
			String[] requestUriBits = requestUri.split("/");

			if (requestUriBits.length < 5) {
				response.getWriter().append("부적절한 요청입니다.");
				return;
			}

			// 컨트롤러
			String controllerName = requestUriBits[3];
			String actionMethodName = requestUriBits[4];

			if (controllerName.equals("article")) {
				ArticleController controller = new ArticleController(request, response, conn);

				if (actionMethodName.equals("list")) {
					controller.actionList();
				} else if (actionMethodName.equals("detail")) {
					controller.actionDetail();
				}

			} else if (controllerName.equals("member")) {
				MemberController controller = new MemberController(request, response, conn);

				if (actionMethodName.equals("join")) {
					controller.actionJoin();
				}
			}

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
