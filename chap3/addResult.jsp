<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
 	boolean saved = (Boolean)request.getAttribute("saved");
 	String msg = "";
 	if(saved) {
 		msg = "사원정보 추가 성공";
 	} else {
 		msg = "사원정보 추가 실패!!!!";
 	}
  %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%= msg%>
<div>
	<a href="/demo/emp?cmd=list">목록보기</a>
</div>
</body>
</html>