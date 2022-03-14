package com.tjoeun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.svc.UserService;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		UserService svc = new UserService(request);
		String viewPath = svc.exec();
		getServletContext().getRequestDispatcher(viewPath)
		.forward(request, response);
	}

}
