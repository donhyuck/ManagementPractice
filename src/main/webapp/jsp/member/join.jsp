<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1 onclick="alert('hi'); retrun false;">회원가입</h1>
	
	<form action="doJoin" method="post">
		<div>
			아이디 : <input name="loginId" type="text" placeholder="아이디를 입력해주세요." autocomplete="off"/>
		</div>
		<div>
			비밀번호 : <input name="loginPw" type="password" placeholder="비밀번호를 입력해주세요." autocomplete="off"/>
		</div>
		<div>
			비밀번호 : <input name="loginPwConfirm" type="password" placeholder="비밀번호를 다시 입력해주세요." autocomplete="off"/>
		</div>
		<div>
			이름 : <input name="name" type="text" placeholder="성함을 입력해주세요." autocomplete="off"/>
		</div>
		
		<div>
			<button type="submit">가입</button>
			<button type="button"> <a href="../home/main">취소</a> </button>
		</div>
	</form>
</body>
</html>