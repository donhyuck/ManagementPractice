<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<% 
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 전체 목록</title>
</head>
<body>
	<h1>게시글 리스트</h1>
	
	<ul>
		<% for(Map<String, Object> articleRow : articleRows) { %>
			<a href="detail?id=<%= (int) articleRow.get("id") %>">
				<li><%= (int) articleRow.get("id") %>번, <%= articleRow.get("regDate") %>, <%= articleRow.get("title") %>, <%= articleRow.get("body") %></li>
			</a>
		<% } %>
	</ul>
	
</body>
</html>