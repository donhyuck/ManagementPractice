<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<h1>로그인</h1>

	<!-- 입력확인 영역 -->
	<script>
		var LoginForm__submitDone = false;
		
		function LoginForm__submit(form) {
			
			if (LoginForm__submitDone) {
				alert('처리중 입니다.');
				return;
			}
			
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();
				return;
			}
			
			form.loginPw.value = form.loginPw.value.trim();
			if (form.loginPw.value.length == 0) {
				alert('로그인 비밀번호를 입력해주세요.');
				form.loginPw.focus();
				return;
			}
			
			form.sumbit();
			LoginForm__submitDone = true;
		}
	</script>

	<form action="doLogin" method="post"
		onsubmit="LoginForm__submit(this); return false;">
		<div>
			아이디 : <input name="loginId" type="text" placeholder="아이디를 입력해주세요." />
		</div>
		<div>
			비밀번호 : <input name="loginPw" type="password" placeholder="비밀번호를 입력해주세요."/>
		</div>
		<div>
			<button type="submit">로그인</button>
			<button type="button">
				<a href="/MP/menu/home/main">취소</a>
			</button>
		</div>
	</form>
</body>
</html>