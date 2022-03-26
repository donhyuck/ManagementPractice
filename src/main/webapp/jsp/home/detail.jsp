<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
	<div><a href="list">목록으로 가기</a></div>
	
	<div>번호 : <%= (int) articleRow.get("id") %></div>
	<div>날짜 : <%= (LocalDateTime) articleRow.get("regDate") %></div>
	<div>제목 : <%= (String) articleRow.get("title") %></div>
	<div>내용 : <%= (String) articleRow.get("body") %></div>
</body>
</html>