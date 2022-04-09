package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.dto.Member;
import com.ldh.java.mp.service.HomeService;

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

		loginCheck();
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

	// 로그인 상태 확인
	public void loginCheck() {

		boolean isLogined = false;
		int loginedMemberId = getLoginedMemberId();
		Member loginedMember = null;

		if (getLoginedMemberId() != -1) {
			isLogined = true;
			loginedMemberId = getLoginedMemberId();
			loginedMember = homeService.getLoginedMember(loginedMemberId);
		}

		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);
	}

	// 로그인 아이디 가져오기
	public int getLoginedMemberId() {

		HttpSession session = request.getSession();
		int loginedMemberId = -1;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		return loginedMemberId;
	}
}
