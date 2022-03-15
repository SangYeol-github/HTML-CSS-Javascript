<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String msg = "Hello";
	request.setAttribute("msg", "Hello");
	//Scope Object(영역객체) : pageContext, request, session, application
	//page -> application 작은 영역부터 찾음
	pageContext.setAttribute("msg","Wolrd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 테스트</title>
</head>
<body>
	<!-- EL(Expression Language)표현 언어, 화면에 표시 -->
	<div>Hello</div> <!-- 정적인 문자열 -->
	<div><%="Hello" %></div> <!-- 동적인 문자열 -->
	<div><%=msg %></div> <!-- 동적인 문자열 -->
	<div><%=(String)request.getAttribute("msg")%></div>
	<%
		out.println("Hello");
		out.println(msg);
		String msg1 = (String)request.getAttribute("msg");
		out.println(msg1); //가져오지 못함.
	%>
	<div>
		${ msg } <!-- JSP만 가능한 EL 영역 어디든 가져옴 --> 
	</div>
	<div>
		${ pageScope.msg } <!-- 영역명시시 성능 증가 -->
	</div>
	<div>
		${ 1 + 5 } <!-- 정보처리 가능 -->
	</div>
	<div> 1 + 5 </div>
</body>
</html>