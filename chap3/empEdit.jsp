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
<title>Insert title here</title>
</head>
<body>
	<h3>사원정보 수정</h3>
<form method="post" action="/demo/emp">	<%--라벨: 문자열 과 인풋태그:입력박스 가 연결된다.--%>
	<input type = "hidden" name ="cmd" value="update">
	<div><label for="num">번호</label> <input type="text" id="num" name="num" value="<%=emp.getNum() %>" readonly></div>
	<div><label for="name">이름</label> <input type="text" id="name" name="name" value="<%=emp.getName() %>" disabled></div>
	<div><label for="phone">전화</label>
		 <input type="text" id="phone" name="phone" value="<%=emp.getPhone() %>"></div>
	<div><label for="email">메일</label>
		 <input type="text" id="email" name="email" value="<%=emp.getEmail() %>"></div>
	<div>&nbsp;</div>
	<div>
		<button type ="submit">수정</button>
		<button type ="submit">취소</button>
	</div>
</form>	
</body>
</html>