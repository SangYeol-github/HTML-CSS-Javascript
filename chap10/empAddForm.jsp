<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가</title>
<style type="text/css">
	h3 { width:fit-content; margin:1em auto; }
	input[type=text] { width: 150px; }
	form { width: fit-content; padding:1em; margin:0 auto; border:1px solid black; }
	label { margin-right:1em; text-align: right; }
	nav { width:fit-content; margin:1em auto; }
	label { display:inline-block; width:50px; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script></head>
<script>
	function insert(){
		if($('#num').val()=='' ||
				$('#ename').val()=='' ||
				$('#deptno').val()=='' ||
				$('#hiredate').val()=='' ||
				$('#sal').val()=='')
		{
			alert('모든 항목은 필수로 입력해야 합니다');
			return false;
		}
		
		var serData = $('#addForm').serialize();
		$.ajax({
			url:'/demo/mem',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				var msg = '';
				if(res.inserted){
					msg = '추가 성공!';
				}else{
					msg = '추가 실패';
					if(res.cause != ''){
						msg += ',    원인: ' + res.cause;
					}
				}
				alert(msg);
				location.href='/demo/paging?cmd=list';
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
		
		return false;
	}
</script>
<body>
<h3>사원정보 추가</h3>
<form id="addForm" onsubmit="return insert();">
<input type="hidden" name="cmd" value="insert">
<div><label for="num">번호</label>
	<input type="text" id="num" name="num" value="11">
</div>
<div><label for="ename">이름</label>
	<input type="text" id="ename" name="ename" value="Elise">
</div>
<div><label for="deptno">부서</label>
	<input type="text" id="deptno" name="deptno" value="50">
</div>
<div><label for="hiredate">입사일</label>
	<input type="text" id="hiredate" name="hiredate" value="2022-03-22">
</div>
<div><label for="sal">급여</label>
	<input type="text" id="sal" name="sal" value="34500">
</div>
<nav>
	<button type="submit">저장</button> <button type="reset">취소</button>
</nav>
</form>
</body>
</html>