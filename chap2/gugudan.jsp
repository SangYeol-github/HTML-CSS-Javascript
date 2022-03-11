<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%--  jsp 액션태그 빈객체를 생성해준다라는 태그 --%>
<jsp:useBean id="gugudan" class="com.tjoeun.bean.GugudanBean" scope="session"/>
<%--	/*Scriptlet : Java 문법을 가능하게 하는 태그 동적인 컨텐츠를 위해 사용 */
	String sDan = request.getParameter("dan"); /* request에 브라우져에서 입력된 요청값이 들어있다 */
		
	if(sDan == null || sDan.equals("")){
		sDan = "2";
	}
	int dan = Integer.parseInt(sDan);
--%>
<%-- 위의 문법을 자바파일로 옮기고 호출한 것.
	int dan = gugudan.getDan();
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>구구단 학습 페이지</title>
</head>
<body>
<%--
	for(int i = 1; i <= 9; i++){
		out.print(String.format("%d * %d = %d<br>", dan, i, dan * i));
	}
--%>
<%-- 
	String str = gugudan.getGugu(dan); 모델1
	out.println(str);
--%>
<%-- 위의 방식들을 생략한 방법. --%>
<%= gugudan.getGugu(gugudan.getDan(request)) %>

<p>
<a href = "gugudan.jsp?dan=2">2</a>
<a href = "gugudan.jsp?dan=3">3</a>
<a href = "gugudan.jsp?dan=4">4</a>
<a href = "gugudan.jsp?dan=5">5</a>
<a href = "gugudan.jsp?dan=6">6</a>
<a href = "gugudan.jsp?dan=7">7</a>
<a href = "gugudan.jsp?dan=8">8</a>
<a href = "gugudan.jsp?dan=9">9</a>
</p>
</body>
</html>