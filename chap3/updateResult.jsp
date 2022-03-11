<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean updated = (Boolean) request.getAttribute("updated");
	String msg = null;
	if(updated) {
		msg = "사원정보 수정 성공";
	} else {
		msg = "사원정보 수정 실패";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 수정 결과</title>
</head>
<body>
	<h3>사원정보 수정 결과</h3>
<%= msg%>
<div><a href = "/demo/emp?cmd=list">목록보기</a></div>	
</body>
</html>