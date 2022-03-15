<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	List<String> list = new ArrayList<>();
	list.add("Scott");
	list.add("Blake");
	list.add("Jone");
	list.add("Smith");
	list.add("King");
	
	pageContext.setAttribute("list", list);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<String> names = (List<String>)pageContext.getAttribute("list");
		for(int i=0; i<names.size(); i++) {
			String name = names.get(i); %>
			<div><%= name %></div>
	<%	
		}
	%>
	
	
</body>
</html>