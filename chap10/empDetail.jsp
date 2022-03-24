<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 상세정보</title>
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
<script>
	function delMem(num){
		if(!confirm(num + ' 번 사원정보를 삭제하시겠어요?')){
			return;
		}
		
		var obj = {};
		obj.cmd = 'delete';
		obj.num = num;
		
		$.ajax({
			url:'/demo/mem',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(res.deleted ? '삭제 성공' : '삭제 실패');
				location.href='/demo/paging?cmd=list';
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
	}
</script>
</head>
<body>
<c:if test="${paging.deptno==0}">
	<c:set var="deptno" value="" />
</c:if>
<c:if test="${paging.deptno!=0}">
	<c:set var="deptno" value="#{paging.deptno}" />
</c:if>
	<main>
		<h3>사원 상세정보</h3>
		<div><label>번호</label> ${paging.empno}</div>
		<div><label>이름</label> ${paging.ename}</div>
		<div><label>부서</label> ${deptno}</div>
		<div><label>입사</label> ${paging.hiredate}</div>
		<div><label>급여</label> ${paging.sal}</div>
	</main>
	<nav>
		[<a href="paging?cmd=edit&num=${paging.empno}">수정</a>]
		[<a href="javascript:delMem(${paging.empno});">삭제</a>]
		[<a href="paging?cmd=list">목록</a>] 
	</nav>
</body>
</html>