<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 상세보기</title>
<style>
	main { width:fit-content; padding:30px; margin:0 auto; border:1px solid black; }
	main > div { border-bottom:1px dashed black; margin:10px; }
	label { margin-right:50px; }
	h3 { width:fit-content; margin:0 auto;  border-bottom:3px double black; 
		margin-bottom:30px;
	}
	nav { width:fit-content; margin:20px auto; }
	nav > a { text-decoration: none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<script>
	function delMem(num) {
		if(!confirm('정말로  삭제하시겠습니까?')) {
			alert('정상적으로 취소했습니다.');
			return;
		}
		var obj = {};
		obj.cmd = 'delete';
		obj.num = num;
		
		$.ajax({
			url : '/demo/mem',
			method : 'post',
			cache : false,
			data : obj,
			dataType : 'json',
			success:function(res){
				if(res.deleted) {
					alert('삭제 완료');
				} else {
					alert('삭제 실패');
				}
				location.href = '/demo/mem?cmd=list';
			},
			error:function(xhr, status, err) {
				alert('err: ' + err);
			}
		}); 
	}
</script>
<body>
	<c:if test = "${mem.deptno==0 }">
		<c:set var = "deptno" value = "" />
	</c:if>
	<c:if test = "${mem.deptno!=0 }">
		<c:set var = "deptno" value = "#{mem.deptno }" />
	</c:if>
		<main>
			<h3>사원정보 상세보기</h3>
			<div><label>번호</label>${mem.empno}</div>
			<div><label>이름</label>${mem.ename}</div>
			<div><label>부서</label>${deptno}</div>
			<div><label>입사</label>${mem.hiredate}</div>
			<div><label>급여</label>${mem.sal}</div>
		</main>
		<nav>
			[<a href = "mem?cmd=edit&num=${mem.empno}">수정</a>]
			[<a href = "javascript:delMem(${mem.empno});">삭제</a>]
			[<a href = "mem?cmd=list">목록</a>]			
		</nav>
</body>
</html>