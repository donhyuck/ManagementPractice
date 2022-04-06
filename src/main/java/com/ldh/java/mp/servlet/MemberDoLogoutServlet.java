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
import com.ldh.java.mp.exception.SQLErrorException;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

@WebServlet("/member/doLogout")
public class MemberDoLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글 출력
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 로그인 정보를 세션으로 관리
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		response.getWriter()
				.append(String.format("<script> alert('로그아웃되었습니다.'); location.replace('../home/main'); </script>"));
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
