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
<title>게시글 수정 페이지</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<div><a href="list">목록으로 가기</a></div>
	
	<!-- 수정 페이지 구현 -->
</body>
</html>