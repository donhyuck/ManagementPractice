<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ldh.java.mp.dto.Article" %>
<% 
List<Article> articles = (List<Article>) request.getAttribute("articles");
int cPage = (int) request.getAttribute("page");
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
	
	<%@ include file="../part/topbar.jspf" %>
	
	<div>
		<a href="write">게시글 작성</a>
	</div>
	<hr />
	
	<table border="1">
		<colgroup>
			<col width="60"/>
			<col width="150" />
			<col width="90" />
		</colgroup>
		
		<!-- 게시글 목록 영역 -->
		<thead>
			<tr>
				<th>번호</th>
				<th>등록일</th>
				<th>제목</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ articles }" var="article">
				<tr>
					<td  align="center">${ article.id }</td>
					<td  align="center">${ article.regDate.toLocalDate() }</td>
					<td><a href="/MP/menu/article/detail?id=${ article.id }">${ article.title }</a></td>
					<td><a href="/MP/menu/article/modify?id=${ article.id }">수정</a></td>
					<td><a href="/MP/menu/article/doDelete?id=${ article.id }">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!--  목록의 페이지 노출 갯수 제한 -->
	<c:set var="pageMenuSize" value="${ 10 }" />
	<c:set var="cPage" value="<%= cPage %>" />
	<c:set var="totalpage" value="<%= totalpage %>" />
		
	<div>
		<c:if test="${ cPage > 1 }">
			<a href="/MP/menu/article/list?page=${ 1 }">◀</a>
		</c:if>
		
		<c:set var="from" value="${ cPage - pageMenuSize }" />
		<c:if test="${ from < 1 }">
			<c:set var="from" value="${ 1 }" />
		</c:if>
		
		<c:set var="end" value="${ cPage + pageMenuSize }" />
		<c:if test="${ end > totalpage }">
			<c:set var="end" value="${ totalpage }" />
		</c:if>
	
		<c:forEach var="i" begin="${ from }" end="${ end }">
			<a href="/MP/menu/article/list?page=${i}"> ${ i } </a>
		</c:forEach>
		
		<c:if test="${ cPage < totalpage }">
			<a href="/MP/menu/article/list?page=${ totalpage }">▶</a>
		</c:if>
	</div>

</body>
</html>