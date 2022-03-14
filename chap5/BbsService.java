package com.tjoeun.svc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.vo.BoardVO;

public class BbsService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BbsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String exec() 
	{
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(request.getSession().getAttribute("uid")==null) {
			request.getSession().setAttribute("target", "/demo/bbs?cmd=inputForm");
			viewPath = "/login/loginForm.jsp";
		}
		else if(cmd==null || cmd.equals("list")) {
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			request.setAttribute("list", list);
			viewPath = "/bbs/board_list.jsp";
		}
		else if(cmd.equals("inputForm")) {
			viewPath = "/bbs/board_input.jsp";
		}
		else if(cmd.equals("save")) {
			// 작성자, 제목, 글내용, 작성일
			String uid = (String)request.getSession().getAttribute("uid");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			
			
			java.util.Date today = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());

			BoardVO board = new BoardVO();
			
			board.setAuthor(uid);
			board.setTitle(title);
			board.setContents(contents);
			board.setDate(sqlDate);
			
			BoardDAO dao = new BoardDAO();
			boolean saved = dao.save(board);
			try {
				PrintWriter out = response.getWriter();
				out.print( String.format("{\"saved\":%b}", saved) );
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("detail")) {
			String strNum = request.getParameter("num");
			BoardVO board = new BoardDAO().getBoard(strNum);
			request.setAttribute("board", board);
			viewPath = "/bbs/board_detail.jsp";
		}
		else if(cmd.equals("edit")) {
			String strNum = request.getParameter("num");
			BoardVO board = new BoardDAO().getBoard(strNum);
			request.setAttribute("board", board);
			viewPath = "/bbs/board_edit.jsp";
		}
		else if(cmd.equals("update")) {
			//파라미터 접수, BoardVO에 저장, DAO에게 갱신요청, 그 결과를 json문자열화
			int num = Integer.parseInt( request.getParameter("num"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			BoardVO board = new BoardVO();
			board.setNum(num);
			board.setTitle(title);
			board.setContents(contents);
			
			BoardDAO dao = new BoardDAO();
			boolean updated = dao.update(board);
			//System.out.println("Service updated:"+updated);
			try {
				PrintWriter out = response.getWriter();
				out.printf("{\"updated\":%b}", updated);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("delete")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDAO dao = new BoardDAO();
			boolean deleted = dao.delete(num);
			try {
				PrintWriter out = response.getWriter();
				out.printf("{\"deleted\":%b}", deleted);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return viewPath;
	}  // end of exec() method

}
