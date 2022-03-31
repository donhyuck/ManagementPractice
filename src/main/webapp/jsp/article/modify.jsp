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
	
	<form action="doModify" method="post">
		<!-- 
		<input name="id" type="hidden" value="<%= articleRow.get("id") %>" />
		<input name="id" type="hidden" value="<%= Integer.parseInt(request.getParameter("id")) %>" />  -->
		<!-- el표기법 -->
		<input name="id" type="hidden" value="${ param.id }" />
		<div>번호 : <%= (int) articleRow.get("id") %></div>
		<div>날짜 : <%= (LocalDateTime) articleRow.get("regDate") %></div>
	
		<div>
			제목 : <input name="title" type="text" value="<%= (String) articleRow.get("title") %>" autocomplete="off" placeholder="제목을 입력해주세요."/>
		</div>
		<div>
			내용 : <textarea name="body" autocomplete="off" placeholder="내용을 입력해주세요."><%= (String) articleRow.get("body") %></textarea>
		</div>
		
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>