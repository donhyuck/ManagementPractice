<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ldh.java.mp.dto.Article" %>
<% 
Article article = (Article) request.getAttribute("article");
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
	
	<form action="doModify" method="post">
		
		<input name="id" type="hidden" value="${ param.id }" />
		<div>번호 : <%= article.getId() %></div>
		<div>날짜 : <%= article.getRegDate() %></div>
	
		<div>
			제목 : <input name="title" type="text" value="<%= article.getTitle() %>" autocomplete="off" placeholder="제목을 입력해주세요."/>
		</div>
		<div>
			내용 : <textarea name="body" autocomplete="off" placeholder="내용을 입력해주세요."><%= article.getBody() %></textarea>
		</div>
		
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>