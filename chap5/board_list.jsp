<%@page import="com.tjoeun.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 리스트</title>
<style>
	table { border:1px solid black; border-spacing: 0; margin:0 auto; }
	table>caption {font-size: x-large; margin-bottom: 10px; }
	th { border:1px solid black; border-bottom:3px double black; 
		background-color: #dddddd;
	}
	td { border-bottom:1px dashed black; border-right:1px dashed black;}
	th,td { padding:5px 10px; }
	td:nth-child(2) { width:200px; }
	td:last-child { border-right:none;}
	tr:last-child > td { border-bottom:none; }
	a { text-decoration: none; }
	div#link { width:fit-content; margin:0 auto; margin-top:20px; }
</style>
</head>
<body>
<table>
<caption>게시판 리스트</caption>
<tr><th>번호</><th>제 목</th><th>작성자</th><th>날 짜</th></tr>
<%
	for(int i=0;i<list.size();i++) 
	{
		BoardVO board = list.get(i);%>
		<tr>
			<td><%=board.getNum() %></td>
			<td><a href="/demo/bbs?cmd=detail&num=<%=board.getNum()%>">
								<%=board.getTitle()%></a></td> 
			<td><%=board.getAuthor() %></td>
			<td><%=board.getDate() %></td>
		</tr>
<%	}
%>
</table>
<div id="link">
	[<a href="/demo/bbs?cmd=inputForm">게시판 글 추가</a>]
</div>
</body>
</html>