<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<h1>게시글 작성</h1>
	
	<form action="doWrite" method="post">
		<div>
			제목 : <input name="title" type="text" placeholder="제목을 입력해주세요." autocomplete="off"/>
		</div>
		<div>
			내용 : <textarea name="body" placeholder="내용을 입력해주세요." autocomplete="off"></textarea>
		</div>
		<div>
			<button type="submit">등록</button>
			<a href="list">목록으로 돌아가기</a>
		</div>
	</form>
</body>
</html>