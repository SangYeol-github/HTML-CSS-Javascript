package com.tjoeun.svc;

import javax.servlet.http.HttpServletRequest;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.vo.UserVO;

public class UserService {
	
	private HttpServletRequest request;
	
	public UserService(HttpServletRequest request) {
		this.request = request;
	}

	public String exec() {
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(cmd==null || cmd.equals("loginform")) {
			return "/login/loginForm.jsp";
		} else if(cmd.equals("login")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			
			UserDAO dao = new UserDAO();
			boolean ok = dao.login(new UserVO(uid,pwd));
			
			if(ok) {
				request.getSession().setAttribute("uid", uid);				
			}
			
			request.setAttribute("ok", ok);
			return "/login/loginResult.jsp";
		}
		return null;
	}

}
