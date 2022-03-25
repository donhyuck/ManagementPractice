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
	<h1>게시글 리스트 v1</h1>
	<!-- 하나씩 가져와서 조각마다 빼기 -->
	<ul>
		<li><%= articleRows.get(0).get("id") %>번, <%= articleRows.get(0).get("regDate") %>, <%= articleRows.get(0).get("title") %>, <%= articleRows.get(0).get("body") %></li>
		<li><%= articleRows.get(1).get("id") %>번, <%= articleRows.get(1).get("regDate") %>, <%= articleRows.get(1).get("title") %>, <%= articleRows.get(1).get("body") %></li>
		<li><%= articleRows.get(2).get("id") %>번, <%= articleRows.get(2).get("regDate") %>, <%= articleRows.get(2).get("title") %>, <%= articleRows.get(2).get("body") %></li>
	</ul>
	
	<h1>게시글 리스트 v2</h1>
	<!-- 반복문으로 가져와서 조각마다 빼기 -->
	<ul>
		<% for(int i = 0; i< articleRows.size(); i++) { %>
			<li><%= articleRows.get(i).get("id") %>번, <%= articleRows.get(i).get("regDate") %>, <%= articleRows.get(i).get("title") %>, <%= articleRows.get(i).get("body") %></li>
		<% } %>
	</ul>
	
	<h1>게시글 리스트 v3</h1>
	<!-- v2를 간결하게 -->
	<ul>
		<% for(int i = 0; i< articleRows.size(); i++) { %>
			<% Map<String, Object> articleRow = articleRows.get(i);%>
			<li><%= articleRow.get("id") %>번, <%= articleRow.get("regDate") %>, <%= articleRow.get("title") %>, <%= articleRow.get("body") %></li>
		<% } %>
	</ul>
	
	<h1>게시글 리스트 v4</h1>
	<!-- 향상된 for문 형태 -->
	<ul>
		<% for(Map<String, Object> articleRow : articleRows) { %>
			<li><%= articleRow.get("id") %>번, <%= articleRow.get("regDate") %>, <%= articleRow.get("title") %>, <%= articleRow.get("body") %></li>
		<% } %>
	</ul>
	
</body>
</html>