package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldh.java.mp.service.MemberService;

public class MemberController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private MemberService memberService;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;

		memberService = new MemberService(conn);
	}

	public void showJoinPage() throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
	}

	public void actionJoin() {

	}

}
