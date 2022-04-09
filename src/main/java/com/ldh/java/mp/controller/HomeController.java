package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.dto.Member;
import com.ldh.java.mp.service.HomeService;
import com.ldh.java.mp.service.MemberService;
import com.ldh.java.mp.util.DBUtil;
import com.ldh.java.mp.util.SecSql;

public class HomeController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private HomeService homeService;

	public HomeController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;

		homeService = new HomeService(conn);
	}

	// 메인 페이지 보기
	public void showMainPage() throws ServletException, IOException {

		// 로그인 상태 확인
		loginCheck();

		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

	private void loginCheck() {

		HttpSession session = request.getSession();

		boolean isLogined = false;
		int loginedMemberId = -1;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {

			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = homeService.getLoginedMember(loginedMemberId);

		}

		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);

	}
}
