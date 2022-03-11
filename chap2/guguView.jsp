<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>요청 구구단 보기</title>
</head>
<body>
<h3>요청 구구단 보기</h3>
<%
	String sGugu = (String)request.getAttribute("g");
%>
<%--출력을 위한 태그--%>
<%= sGugu %>
</body>
</html>