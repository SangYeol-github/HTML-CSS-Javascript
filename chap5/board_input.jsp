<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function save()
	{
		var serData = $('#inputForm').serialize();
		$.ajax({
			url:'/demo/bbs',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				if(res.saved) {
					alert('게시판 글 저장 성공');
				}else{
					alert('글 저장 실패');
				}
				location.href="/demo/bbs?cmd=list";
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
		return false;
	}
</script>
<style>
	div { margin:5px; }
</style>
</head>
<body>
<h3>게시판 글 쓰기</h3>
<form id='inputForm' action="/demo/bbs" method="post" onsubmit="return save();">
	<input type="hidden" name="cmd" value="save">
<div><label for="title">글 제목</label>
	<input type="text" id="title" name="title" value="제목 테스트">
</div>
<div>
	<label for="contents" >글 내용</label>
	<textarea id="contents" name="contents" placeholder="글 입력..."
			rows="5" cols="35">게시판 활용 부탁해요~</textarea>
</div>
<div>
	<button type="submit">저장</button>
	<button type="reset">취소</button>
</div>
</form>
</body>
</html>