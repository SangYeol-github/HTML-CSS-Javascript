<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정하기</title>
<style>
	label { font-size: large; font-weight: bold;}
	#title, #contents { width:300px; margin-bottom:10px; }
	h3, form { width:fit-content; margin:10px auto; }
	form { border:1px solid black; padding:20px; }
	div:last-child { text-align:center; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
/*
$(function(){
	alert('jQuery Ready!');
});*/
	function update(){
		if(!confirm('정말로 이 글을 수정하시겠어요?')){ 
			alert('정상적으로 취소했습니다');
			return false;
		}
		var serData = $('#updateForm').serialize();
		$.ajax({
			url: '/demo/bbs',
			method:'post',
			cache:false,
			data:serData,
			dataType:'json',
			success:function(res){
				alert(res.updated ? '수정 성공' : '수정 실패');
			},
			error:function(xhr,status,err ){
				alert('에러:'+err);
			}
		});

		return false;
	}
</script>
</head>
<body>
<h3>제목과 내용을 변경해주세요</h3>
<form id="updateForm" onsubmit="return update();">
	<input type="hidden" name="cmd" value="update">
	<input type="hidden" name="num" value="<%=board.getNum()%>">
	<div><label for="title">제목</label></div>
	<div>
		<input type="text" id="title" name="title" value="<%=board.getTitle()%>">
	</div>
	<div><label for="contents">내용</label></div>
	<div>
		<textarea id="contents" name="contents" rows="10" ><%=board.getContents()%></textarea>
	</div>
	<div>
		<button type="submit">저장</button>
		<button type="reset">취소</button>
	</div>
</form>
</body>
</html>