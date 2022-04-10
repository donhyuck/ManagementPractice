package com.ldh.java.mp.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldh.java.mp.dto.Member;
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

	// 회원가입 페이지 보기
	public void showJoinPage() throws ServletException, IOException {

		// 로그아웃 확인
		HomeController homeController = new HomeController(request, response, conn);
		int loginedMemberId = homeController.getLoginedMemberId();

		if (loginedMemberId != -1) {
			response.getWriter().append(String
					.format("<<script>alert('로그아웃 후 이용해주세요.'); location.replace('/MP/menu/home/main'); </script>"));
			return;
		}

		request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
	}

	// 회원가입 하기
	public void actionJoin() throws IOException {

		// 입력받은 회원정보
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String name = request.getParameter("name");

		// 로그인 아이디 중복체크
		int isLoginIdExist = memberService.isLoginIdExist(loginId);

		if (isLoginIdExist != 0) {
			response.getWriter().append(
					String.format("<script> alert('%s (은)는 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId));
			return;
		}

		// 회원가입하기
		int id = memberService.join(loginId, loginPw, name);

		response.getWriter().append(String.format(
				"<<script>alert('%s님 %d번 회원으로 가입되었습니다.'); location.replace('../home/main'); </script>", name, id));
	}

	// 로그인 페이지 보기
	public void showLoginPage() throws ServletException, IOException {

		request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
	}

	// 로그인 하기
	public void actionLogin() throws IOException {

		// 입력받은 회원 로그인 정보
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		// 로그인 아이디 중복체크
		int isLoginIdExist = memberService.isLoginIdExist(loginId);

		if (isLoginIdExist == 0) {
			response.getWriter().append(
					String.format("<script> alert('%s(은)는 등록되지 않은 회원입니다.'); history.back(); </script>", loginId));
			return;
		}

		// 비밀번호 확인
		Member member = memberService.getMemberByLoginId(loginId);

		if (member.getLoginPw().equals(loginPw) == false) {
			response.getWriter().append("<script> alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>");
			return;
		}

		// 로그인 정보를 세션으로 관리
		HttpSession session = request.getSession();
		session.setAttribute("loginedMemberId", member.getId());

		response.getWriter()
				.append(String.format(
						"<script> alert('%s님 로그인되었습니다.'); location.replace('/MP/menu/home/main'); </script>",
						member.getName()));
		return;

	}

	// 로그아웃 하기
	public void actionLogout() throws IOException {

		// 로그인 정보를 세션으로 관리
		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		response.getWriter().append(
				String.format("<script> alert('로그아웃되었습니다.'); location.replace('/MP/menu/home/main'); </script>"));
		return;
	}

	// 비밀번호 변경 페이지 보기
	public void showPWChangePage() throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/member/pwChange.jsp").forward(request, response);
	}

	// 비밀번호 변경하기
	public void actionPWChange() throws IOException {

		// 현재 비밀번호 가져오기
		HomeController homeController = new HomeController(request, response, conn);
		Member loginedMember = homeController.getMemberById();

		// 입력받은 회원 로그인 정보
		String loginPwForCheck = request.getParameter("loginPwForCheck");
		String newLoginPw = request.getParameter("newLoginPw");

		// 입력한 비밀번호와 회원 비밀번호 일치여부 확인
		if (!loginedMember.getLoginPw().equals(loginPwForCheck)) {
			response.getWriter()
					.append(String.format("<script> alert('비밀번호가 들렸습니다. 다시 확인해주세요.'); location.replace('/MP/menu/home/myInfo'); </script>"));
			return;
		}

		// 새 비밀번호 적용
		int id = loginedMember.getId();
		memberService.pwChange(id, newLoginPw);

		HttpSession session = request.getSession();
		session.removeAttribute("loginedMemberId");

		response.getWriter().append(String.format(
				"<script> alert('%s님 비밀번호가 변경되었습니다. 다시 로그인해주세요.'); location.replace('/MP/menu/home/main'); </script>",
				loginedMember.getName()));
		return;
	}
}
