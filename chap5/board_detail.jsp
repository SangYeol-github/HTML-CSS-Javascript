<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 보기</title>
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
<script>
	function edit(num)
	{
		var url = "/demo/bbs?cmd=edit&num="+num;
		location.href=url;
	}
	
	function del_board(num){
		if(!confirm('현재 글을 삭제하시겠어요?')){
			return;
		}
		
		var obj = {};
		obj.cmd = 'delete';
		obj.num = num;
		
		$.ajax({
			url:'/demo/bbs',
			method:'post',
			cache:false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(res.deleted ? '삭제 성공' : '삭제 실패');
				location.href='/demo/bbs?cmd=list';
			},
			error:function(xhr,status,err){
				alert('에러:'+err);
			}
		});
	}
</script>
</head>
<body>
<h3>게시판 글 보기</h3>
<table>
<tr><th> 번호 </th><td> <%=board.getNum()%></td></tr>
<tr><th> 제목 </th><td> <%=board.getTitle() %></td></tr>
<tr><th> 작성자 </th><td> <%=board.getAuthor() %></td></tr>
<tr><th> 작성일 </th><td> <%=board.getDate() %></td></tr>
<tr><th> 내용 </th><td id="contents"> <%=board.getContents() %></td></tr>
</table>
<hr>
<div id="links">
	<button type="button" onclick="edit(<%=board.getNum()%>);">수정</button>
	<button type="button" onclick="del_board(<%=board.getNum()%>);">삭제</button>
	<button type="button" onclick="location.href='/demo/bbs'">목록</button>
</div>
</body>
</html>