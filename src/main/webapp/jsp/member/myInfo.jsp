<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ldh.java.mp.dto.Member" %>
<%
boolean isLogined = (boolean) request.getAttribute("isLogined");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
Member loginedMember = (Member) request.getAttribute("loginedMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보관리</title>
</head>
<body>
	<h1>내 정보관리</h1>
	<a href="/MP/menu/article/list">게시글 리스트</a>
	<a href="/MP/menu/home/main">메인 페이지</a>
	<hr />
	
	<ul>
		<li>회원번호 : <%= loginedMember.id %></li>
		<li>가입날짜 : <%= loginedMember.regDate %></li>
		<li>아이디 : <%= loginedMember.loginId %></li>
		<li>성명 : <%= loginedMember.name %></li>
	</ul>

</body>
</html>