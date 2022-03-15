<%@ page import = "com.tjoeun.vo.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
	label { font-size: large; font-weight: bold;}
	#title, #contents { width:300px; margin-bottom:10px; }
	h3, form { width:fit-content; margin:10px auto; }
	form { border:1px solid black; padding:20px; }
	div:last-child { text-align:center; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>게시글 수정하기</title>
<script>
	function update() {
		
		if(!confirm('정말로 수정하시겠습니까?')) {
			alert('취소했습니다.');
			return false;
		}
		var serData = $('#updateForm').serialize();
		$.ajax({
			url : '/demo/bbs',
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
				location.href = '/demo/bbs?cmd=list';
			},
			error:function(xhr, status, err) {
				alert('err: ' + err);
			}
		});
		return false;
	}
</script>
</head>
<body>
	<h3>제목과 내용을 변경해주세요</h3>
	<form id = "updateForm" onsubmit="return update();">
		<input type = "hidden" name ="cmd" value = "update">
		<input type = "hidden" name = "num" value = "${ board.num }">
		<div><label for = "title">제목</label></div>
		<div>
			<input type = "text" id = "title" name = "title" value = "${ board.title }">
		</div>
		<div><label for = "contents">내용</label></div>
		<div>
			<textarea id = "contents" name = "contents" rows = "10">${ board.contents } %></textarea>
		</div>
		<div>
			<button type = "submit">저장</button>
			<button type = "reset">취소</button>
		</div>
	</form>
</body>
</html>