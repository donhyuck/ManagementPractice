<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ldh.java.mp.dto.Article" %>
<% 
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 페이지</title>
</head>
<body>
	<h1>게시글 상세</h1>
	
	<%@ include file="../part/topbar.jspf" %>
	
	<div>번호 : <%= (int) articleRow.get("id") %></div>
	<div>날짜 : <%= (LocalDateTime) articleRow.get("regDate") %></div>
	<div>제목 : <%= (String) articleRow.get("title") %></div>
	<div>내용 : <%= (String) articleRow.get("body") %></div>
	
	<div>
		<a href="/MP/menu/article/modify?id=${ param.id }">수정</a>
		<a href="/MP/menu/article/doDelete?id=${ param.id }">삭제</a>
	</div>
</body>
</html>