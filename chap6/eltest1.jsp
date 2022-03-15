<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	BoardVO board = new BoardVO(); //BoardVO to.String() 수정 후 변경하기.
	board.setNum(11);
	board.setAuthor("Lora");
	board.setTitle("게시판 테스트");
	board.setContents("많이 사용해 주세요~");
	
	pageContext.setAttribute("board", board);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 테스트1</title>
</head>
<body>
	<%
		BoardVO b = (BoardVO)pageContext.getAttribute("board");
		out.println("번호 : " + b.getNum());
	%>
	<div>
		제목 : <%= b.getTitle() %>
	</div>
	<div>
		${board.contents }
	</div>
</body>
</html>