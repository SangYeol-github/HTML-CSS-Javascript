<%@page import="com.tjoeun.vo.EmpVO"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	EmpVO emp = (EmpVO) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 상세정보 보기</title>
</head>
<body>
<h3>사원 상세정보</h3>
<div>번호 <%=emp.getNum()%></div>
<div>이름 <%=emp.getName()%></div>
<div>전화번호 <%=emp.getPhone()%></div>
<div>메일 <%=emp.getEmail()%></div>
<hr>
[<a href="/demo/emp?cmd=edit&num=<%=emp.getNum()%>">수정</a>]
[<a href="/demo/emp?cmd=delete&num=<%=emp.getNum()%>">삭제</a>]
</body>
</html>