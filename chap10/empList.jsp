<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 리스트</title>
<style>
	h3 { width:fit-content; margin:10px auto; }
	table{ border:1px solid black; border-spacing: 0; 
		width:500px; margin:0 auto;
	}
	th { border:1px solid black; background-color: #dddddd;}
	th,td { border-right: 1px solid black; }
	th { border-bottom: 3px double black;}
	td { border-bottom: 1px dashed black; padding:3px 10px; }
	td:nth-child(4) { text-align: center; }
	td:last-child { border-right: none; }
	tr:nth-child(odd) { background-color: #eeeeee; }
	tr:last-child >td { border-bottom:none; }
	a { text-decoration: none;  }
	nav { width:fit-content; margin:2em auto; }
	.red { color : red; }
	.black { color : black; }
	div.links { width: fit-content; margin: 5px auto; padding: 0 10px;
		text-align : center; border : 1px solid black; }
	div.links >a { margin-right: 10px;}	
</style>
</head>
<body>
<h3>사원정보 리스트</h3>
<main>
<table>
<tr><th>번호</th><th>성명</th><th>부서</th><th>입사일</th><th>급여</th></tr>
<c:forEach var="e" items="${pgutil.list}">
	<tr>
		<td>${e.empno}</td>
		<td><a href="/demo/paging?cmd=detail&num=${e.empno}">${e.ename}</a></td>
		<td>${e.deptno}</td>
		<td>${e.hiredate}</td>
		<td>${e.sal}</td>
	</tr>
</c:forEach>
</table>
<nav>
	<button type="button" 
		onclick="location.href='paging?cmd=addForm';">사원정보 추가</button>
</nav>
	<nav class = "link">
	<c:forEach var ="n" items="${pgutil.navLinks}">
		<c:if test = "${n==pgutil.currPage }">
			<c:set var = "cls" value = "red" />
		</c:if>
		<c:if test = "${n!=pgutil.currPage }">
			<c:set var = "cls" value = "black" />
		</c:if>
		<a class = "${cls }" href="/demo/paging?page=${n }">${n }</a>
	</c:forEach>
	</nav>
	</main>
</body>
</html>