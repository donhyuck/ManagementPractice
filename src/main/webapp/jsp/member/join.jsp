<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1 onclick="alert('hi'); return false;">회원가입</h1>

	<!-- 입력확인 영역 -->
	<script>
		var JoinForm__submitDone = false;
		
		function JoinForm__submit(form) {
			if (JoinForm__submitDone) {
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
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
			if (form.loginPwConfirm.value.length == 0) {
				alert('로그인 비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();
				return;
			}
			if (form.loginPw.value != form.loginPwConfirm.value) {
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPw.focus();
				return;
			}
			form.name.value = form.name.value.trim();
			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();
				return;
			}
			form.sumbit();
			JoinForm__submitDone = true;
		}
	</script>

	<form action="doJoin" method="post"
		onsubmit="JoinForm__submit(this); return false;">
		<div>
			아이디 : <input name="loginId" type="text" placeholder="아이디를 입력해주세요." />
		</div>
		<div>
			비밀번호 : <input name="loginPw" type="password" placeholder="비밀번호를 입력해주세요."/>
		</div>
		<div>
			비밀번호 확인 : <input name="loginPwConfirm" type="password" placeholder="비밀번호를 다시 입력해주세요."  size="25" />
		</div>
		<div>
			이름 : <input name="name" type="text" placeholder="이름을 입력해주세요." />
		</div>

		<div>
			<button type="submit">가입</button>
			<button type="button">
				<a href="/MP/menu/home/main">취소</a>
			</button>
		</div>
	</form>
</body>
</html>