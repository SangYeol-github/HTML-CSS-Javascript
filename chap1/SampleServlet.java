package com.tjoeun.servlet;	// ServletClass로 html타입의 웹브라우져 응답보내기.

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample")
public class SampleServlet extends HttpServlet {
	
	@Override // Override문법이라는것을 표시하는 키워드.
	// request : 요청을 받고분석, response : 응답으로 출력처리
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()");	// 클라이언트가 요청 할때마다 실행.
		response.setContentType("text/html; charset = utf-8"); // 보내는 텍스트 타입은 html 문자집합은 utf-8로 변경해주는것.
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println(" span { color:navy;}");
		out.println("</style>");	
		out.println("</head>");
		out.println("<body>");
		out.println("<h2><span>Hello</span></h2> your response~");
		out.println("<br>"); // <br> html 줄바꿈 태그
		out.println("응답도착~");		
		out.println("</body>");
		out.println("</html>");
	}
	/*
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");		// 요청하면 받는다.
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");		// 요청하고 보낸다.
		doGet(request, response);
	}*/
}
