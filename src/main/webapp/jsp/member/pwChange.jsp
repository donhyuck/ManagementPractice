<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1 onclick="alert('hi'); return false;">비밀번호 변경</h1>

	<script>
		var pwChangeForm__submitDone = false;
		
		function pwChangeForm__submit(form) {
			if (pwChangeForm__submitDone) {
				alert('처리중 입니다.');
				return;
			}
			form.loginPwForCheck.value = form.loginPwForCheck.value.trim();
			if (form.loginPwForCheck.value.length == 0) {
				alert('현재 비밀번호를 입력해주세요.');
				form.loginPwForCheck.focus();
				return;
			}
			form.newLoginPw.value = form.newLoginPw.value.trim();
			if (form.newLoginPw.value.length == 0) {
				alert('변경할 비밀번호를 입력해주세요.');
				form.newLoginPw.focus();
				return;
			}
			form.sumbit();
			pwChangeForm__submitDone = true;
		}
	</script>

	<form action="doPwChange" method="post"
		onsubmit="pwChangeForm__submit(this); return false;">
		<div>
			현재 비밀번호 : <input name="loginPwForCheck" type="password" placeholder="현재 비밀번호를 입력해주세요." size="30"/>
		</div>
		<div>
			신규 비밀번호 : <input name="newLoginPw" type="password" placeholder="변경할 비밀번호를 입력해주세요."  size="30" />
		</div>

		<div>
			<button type="submit">변경</button>
			<button type="button">
				<a href="/MP/menu/home/myInfo">취소</a>
			</button>
		</div>
	</form>
</body>
</html>