<%@ page import = "com.tjoeun.vo.BoardVO" %>
<%@ page import = "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<title>게시판 리스트</title>
</head>
<body>
	<%-- <c:set var = "list" value = "${ requestScope.list }" scope = "page"/> --%>
	<table>
		<caption>게시판 리스트</caption>
		<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th></tr>
		<c:forEach var = "list" items = "${requestScope.list }">
		<%-- <c:forEace var = "list" items = "${ list }" > --%>
				<tr>
					<td>${ list.num }</td>
					<td><a href = "/demo/bbs?cmd=detail&num=${ list.num }">
						${ list.title }</a></td>
					<td>${ list.author }</td>
					<td>${ list.date }</td>					
		</c:forEach>
	</table>
	<div id = "link">
		[<a href = "/demo/bbs?cmd=inputForm">게시판 글 추가</a>]
	</div>
</body>
</html>