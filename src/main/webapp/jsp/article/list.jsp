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
		
	<table border="1">
	<colgroup>
		<col width="80"/>
		<col width="200"/>
		<col />
	</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>등록일</th>
				<th>제목</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
		<% for(Map<String, Object> articleRow : articleRows) { %>
			<tr>
				<td><%= articleRow.get("id") %></td>
				<td><%= articleRow.get("regDate") %></td>
				<td><%= articleRow.get("title") %></td>
				<td><a href="">삭제</a></td>
			</tr>
		</tbody>
		<% } %>
	</table>

</body>
</html>