
<%@page import="com.tjoeun.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	List<EmpVO> list = (List<EmpVO>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 리스트</title>
	<style>
		table { border : 1px solid black; border-spacing:0; }
		th,td { border : 1px solid black; padding:0.2em; }
		th { border-bottom: 3px solid black; }
		td { border:1px solid black;}
		td { border-bottom: 1px dotted black; }
	</style>
</head>
<body>
<h3>사원정보 리스트</h3>
<table>
<tr><th>번호</th><th>이름</th><th>전화</th><th>이메일</th></tr>
<%
	for(int i=0; i<list.size(); i++) {
		EmpVO emp = list.get(i); %>
		<tr> <%--한행에 td 네개 --%>								<%--서버 번호요청. --%>
			<td><%=emp.getNum()%></td>
			<td><a href="/demo/emp?cmd=find&num=<%=emp.getNum()%>">
						<%=emp.getName()%></a></td>
			
			<td><%=emp.getPhone()%></td>
			<td><%=emp.getEmail()%></td>
		</tr>
<% 		
	}
%>
</table>
<hr>
[<a href ="/demo/empInputForm.jsp">사원정보 추가</a>]
</body>
</html>