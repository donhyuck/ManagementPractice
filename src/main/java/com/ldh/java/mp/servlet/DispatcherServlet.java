package com.ldh.java.mp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.Config;
import com.ldh.java.mp.controller.ArticleController;
import com.ldh.java.mp.controller.HomeController;
import com.ldh.java.mp.controller.MemberController;
import com.ldh.java.mp.exception.SQLErrorException;

@WebServlet("/menu/*")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

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

			HomeController homeController = new HomeController(request, response, conn);
			homeController.loginCheck();
			int loginedMemberId = homeController.getLoginedMemberId();

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
				} else if (actionMethodName.equals("write")) {
					controller.showWritePage();
				} else if (actionMethodName.equals("doWrite")) {
					controller.actionWrite();
				} else if (actionMethodName.equals("modify")) {
					controller.showModifyPage();
				} else if (actionMethodName.equals("doModify")) {
					controller.actionModify();
				} else if (actionMethodName.equals("doDelete")) {
					controller.actionDelete();
				}

			} else if (controllerName.equals("member")) {
				MemberController controller = new MemberController(request, response, conn);

				if (actionMethodName.equals("join")) {
					controller.showJoinPage();
				} else if (actionMethodName.equals("doJoin")) {
					controller.actionJoin();
				} else if (actionMethodName.equals("login")) {
					controller.showLoginPage();
				} else if (actionMethodName.equals("doLogin")) {
					controller.actionLogin();
				} else if (actionMethodName.equals("doLogout")) {
					controller.actionLogout();
				} else if (actionMethodName.equals("pwChange")) {
					controller.showPWChangePage();
				}

			} else if (controllerName.equals("home")) {
				homeController = new HomeController(request, response, conn);

				if (actionMethodName.equals("main")) {
					homeController.showMainPage();
				} else if (actionMethodName.equals("myInfo")) {
					homeController.showMyInfo();
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
