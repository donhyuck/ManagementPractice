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
	
	<!-- 입력확인 영역 -->
	<script>
		var Join__submitDone == false;
		
		function Join__submit(form) {
			
			// 중복처리를 막는다.
			if (Join__submitDone) {
				alert('처리중입니다.');
				return;
			}
			
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요.');
				form.loginId.focus();
				return;
			}
			
			form.loginPw.value = form.loginPw.value.trim();
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();
				return;
			}
			
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
			if (form.loginPwConfirm.value.length == 0) {
				alert('비밀번호를 다시 입력해주세요.');
				form.loginPwConfirm.focus();
				return;
			}
			
			if (form.loginPw.value != form.loginPwConfirm.value) {
				alert('비밀번호가 일치하지 않습니다!');
				form.loginPw.focus();
				return;
			}
			
			form.name.value = form.name.value.trim();
			if (form.name.value.length == 0) {
				alert('이름를 입력해주세요.');
				form.name.focus();
				return;
			}
			
			// 여기까지 도달하면 회원가입을 위한 정보가 입력되고, 비밀번호와 비밀번호 확인이 일치함
			form.submit();
			Join__submitDone = true;
		}
	</script>
	
	<form action="doJoin" method="post" onsubmit="Join__submit(this); return false;">
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
			이름 : <input name="name" type="text" placeholder="이름을 입력해주세요." autocomplete="off"/>
		</div>
		
		<div>
			<button type="submit">가입</button>
			<button type="button"> <a href="../home/main">취소</a> </button>
		</div>
	</form>
</body>
</html>