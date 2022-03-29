<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<% 
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int cpage = (int) request.getAttribute("page");
int totalpage = (int) request.getAttribute("totalpage");
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
				<td><a href="detail?id=<%=articleRow.get("id") %>"><%=articleRow.get("title") %></a></td>
				<td><a href="doDelete?id=<%=articleRow.get("id") %>">삭제</a></td>
			</tr>
		<% } %>	
		</tbody>
	</table>
	
	<style type="text/css">
.page > a.red {
	color: red;
}
</style>

	<div class="page">
		<% for(int i = 1;i<=10;i++) {%>
			<a href="list?page=<%=i%>"><%=i%></a>
		<% } %>
	</div>

</body>
</html>