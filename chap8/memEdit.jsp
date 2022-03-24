<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 수정</title>
<style>
	main { width:fit-content; padding:30px; margin:0 auto; border:1px solid black; }
	main div { border-bottom:1px dashed black; margin:10px; }
	label { margin-right:50px; }
	h3 { width:fit-content; margin:0 auto;  border-bottom:3px double black; 
		margin-bottom:30px;
	}
	nav { width:fit-content; margin:20px auto; }
	nav > a { text-decoration: none; }
	input[type=text] { width:100px; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
	<script>
		function update() {
			var deptno = $('#deptno').val();
			var sal = $('#sal').val();
			if(deptno=='' || sal=='') {
				alert('부서번호, 급여액은 필수항목입니다.');
			}
			if(!confirm('입력된 정보를 수정하시겠어요?')) {
				return false;
			}
			
			var serData = $('#updateForm').serialize();
			$.ajax({
				url : '/demo/mem',
				method : 'post',
				cache : false,
				data : serData,
				dataType : 'json',
				success:function(res) {
					if(res.updated) {
						alert('수정 성공');
					} else {
						alert('수정 실패');
					}
					location.href = '/demo/mem?cmd=list';
				},
				error:function(xhr, status, err) {
					alert('에러: ' + err);
				}
			});
			return false;
		}
	</script>
<body>
	<c:if test= "${mem.deptno==0 }">
		<c:set var = "deptno" value = "" />
	</c:if>
	<c:if test= "${mem.deptno!=0 }">
		<c:set var = "deptno" value = "#{mem.deptno }" />
	</c:if>
	<main>
		<form id = "updateForm" onsubmit = "return update();">
			<input type = "hidden" name = "cmd" value = "update">
			<input type = "hidden" name = "num" value = "${mem.empno }">
		<h3>사원정보 수정</h3>
		<div><label>번호</label>${mem.empno}</div>
		<div><label>이름</label>${mem.ename}</div>
		<div><label>부서</label>
			<input type = "text" id = "deptno" name = "deptno" value = "${deptno }">
		</div>
		<div><label>입사</label>${mem.hiredate}</div>
		<div><label>급여</label>
			<input type = "text" id = "sal" name = "sal" value = "${mem.sal }">
		</div>
		<nav>
			<button type = "submit">수정</button>
			<button type = "reset">취소</button>
		</nav>
		</form>
	</main>
</body>
</html>