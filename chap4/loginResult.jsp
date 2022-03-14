<%@ page contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean ok = (Boolean) request.getAttribute("ok");
	/* String msg = null;
		if(ok) {
			msg = "로그인 성공";
		} else {
			msg = "로그인 실패";
		} */
	String jsonStr = String.format("{\"ok\":%b}",ok);	//json 표기법
%><%= jsonStr%>

<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 결과</title>
	<script>
		var msg = '<%=msg%>';
		alert(msg);
	</script>
</head>
<body>
	<h3>로그인 결과</h3>
<%= msg%>	
</body>
</html> --%>