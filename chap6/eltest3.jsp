<%@page import="java.util.ArrayList"%>
<%@page import="com.tjoeun.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	List<BoardVO> list = new ArrayList<>();
	list.add(new BoardVO(11,"게시판 테스트"));
	list.add(new BoardVO(12,"안녕하세요?"));
	list.add(new BoardVO(13,"방가~"));
	list.add(new BoardVO(14,"감사해요"));
	list.add(new BoardVO(15,"성공하세요"));
	
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 테스트</title>
</head>
<body>
	<div>${ list[0] }</div>
	<div>${ list[1] }</div>
	<div>${ list[1].num }, ${ list[1].title }</div>
	
	<!-- JSPL 루프를 사용하기 위해서 라이브러리가 요구됨 -->
	<!-- https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 -->
	<!-- webapp/WEB-INF /lib/jstl-1.2.jar -->
	<c:forEach begin="0" end="4" varStatus="stauts">
		<div>${list[status.index].num }, ${list[status.index].title }</div>
	</c:forEach>
	
	<c:forEach var = "b" items = "${pageScope.list }">
		<div>${ b.num }, ${ b.title }</div>
	</c:forEach>
</body>
</html>