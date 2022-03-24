package com.tjoeun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] hobby = request.getParameterValues("hobby");
		for(int i=0; i<hobby.length; i++) {
			System.out.println(hobby[i]);
		}
		
		String gender = request.getParameter("gender");
			System.out.println("성별: " + gender);
		
		String subject = request.getParameter("subject");
			System.out.println("선택과목: " + subject);
		
		String color = request.getParameter("mycolor");
			System.out.println("색상선택: " + color);
			
		String birth = request.getParameter("birth");
			System.out.println("생일: " + birth);
			
		String level = request.getParameter("level");
			System.out.println("게임레벨: " + level);
			
		
		
	}

}
