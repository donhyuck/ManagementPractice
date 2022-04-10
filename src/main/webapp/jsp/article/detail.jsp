<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ldh.java.mp.dto.Article" %>
<%@ page import="com.ldh.java.mp.dto.Member" %>
<% 
Article article = (Article) request.getAttribute("article");
String memberName = (String) request.getAttribute("memberName");
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
	
	<div>번호 : ${ article.id }</div>
	<div>날짜 : ${ article.regDate.toLocalDate() } / ${ article.regDate.toLocalTime() }</div>
	<div>제목 : ${ article.title }</div>
	<div>내용 : ${ article.body }</div>
	<div>작성자 : ${ memberName }</div>
	
	<div>
		<a href="/MP/menu/article/modify?id=${ param.id }">수정</a>
		<a href="/MP/menu/article/doDelete?id=${ param.id }">삭제</a>
	</div>
</body>
</html>