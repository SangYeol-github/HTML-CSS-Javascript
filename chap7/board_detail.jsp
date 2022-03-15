<%@ page import = "com.tjoeun.vo.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
	table { border:1px solid black; margin:0 auto; border-spacing: 0;
		border-radius: 5px;	}
	th { border-bottom:1px solid black;  padding:5px; text-align:right; 
		width:70px; background-color:#dddddd; padding-right:20px; }
	td { border-bottom:1px dashed black; padding:5px; width:300px; 
		margin-left:10px; }
	tr:last-child > td { border-bottom:none; }
	h3 { width:fit-content; margin:10px auto; }
	td#contents { height:200px; overflow: scroll; }
	div#links { width:fit-content; margin:20px auto; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>게시판 글 보기</title>
<script>
	
	function edit(num) {
		var url = "/demo/bbs?cmd=edit&num="+num
		location.href = url;
	}
	
	function del_board(num) {
		if(!confirm('정말로  삭제하시겠습니까?')) {
			alert('정상적으로 취소했습니다.');
			return;
		}
		var obj = {};
		obj.cmd = 'delete';
		obj.num = num;
		
		$.ajax({
			url : '/demo/bbs',
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
				location.href = '/demo/bbs?cmd=list';
			},
			error:function(xhr, status, err) {
				alert('err: ' + err);
			}
		});
	}
	
</script>
</head>
<body>
	<c:set var = "board" value = "${requestScope.board}" />
	<h3>게시판 글 보기</h3>
	<table>
		<tr><th>번호</th><td>${ board.num }</td></tr>
		<tr><th>제목</th><td>${ board.title }</td></tr>
		<tr><th>작성자</th><td>${ board.author }</td></tr>
		<tr><th>작성일</th><td>${ board.date }</td></tr>
		<tr><th>내용</th><td id="contents">${ board.contents }</td></tr>
	</table>
	<hr>
	<div id = "links">
		<button type = "button" onclick = "edit(${ board.num })">수정</button>
		<button type = "button" onclick = "del_board(${ board.num })">삭제</button>
		<button type = "button" onclick = "location.href='/demo/bbs'">목록보기</button>
	</div>
</body>
</html>