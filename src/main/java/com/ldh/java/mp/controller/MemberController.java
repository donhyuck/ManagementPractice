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

	public void actionJoin() throws IOException {

		// 입력받은 회원정보
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String name = request.getParameter("name");

		// 로그인 아이디 중복체크
		boolean isJoinableByLoginId = memberService.checkForJoinable(loginId);

		if (isJoinableByLoginId == false) {
			response.getWriter().append(
					String.format("<script> alert('%s (은)는 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId));
			return;
		}

		// 회원가입하기
		int id = memberService.join(loginId, loginPw, name);

		response.getWriter().append(String.format(
				"<<script>alert('%s님 %d번 회원으로 가입되었습니다.'); location.replace('../home/main'); </script>", name, id));
	}

}
