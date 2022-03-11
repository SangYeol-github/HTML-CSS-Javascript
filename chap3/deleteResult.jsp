<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean deleted = (Boolean) request.getAttribute("deleted");
	String msg = null;
	if(deleted) {
		msg = "사원정보 삭제 성공";
	} else {
		msg = "사원정보 삭제 실패";
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>사원정보 삭제 결과</h3>
	<%= msg%>
	<a href = "/demo/emp?cmd=list">목록보기</a>
</body>
</html>