package com.tjoeun.svc;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.vo.BoardVO;

public class BbsService {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BbsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String exec() {
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(request.getSession().getAttribute("uid")==null) {
			request.getSession().setAttribute("target", "/demo/bbs?cmd=inputForm");
			viewPath = "/login/loginForm.jsp";
		
		} else if(cmd==null || cmd.equals("list")) {
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			request.setAttribute("list", list);
			viewPath = "/bbs/board_list.jsp";
		
		} else if(cmd.equals("inputForm")) {
			viewPath = "/bbs/board_input.jsp";
		
		} else if(cmd.equals("save")) {
			
			String uid = (String)request.getSession().getAttribute("uid");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			java.util.Date today = new java.util.Date();
			java.sql.Date sqldate = new java.sql.Date(today.getTime());
			
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			int boardNum = 1;
			if(list.size()!=0) {
				boardNum = list.get(list.size()-1).getNum()+1;
			}
			
			BoardVO board = new BoardVO();
			board.setNum(boardNum);
			board.setAuthor(uid);
			board.setTitle(title);
			board.setContents(contents);
			board.setDate(sqldate);
			
			boolean saved = dao.save(board);
			try {
				PrintWriter pw = response.getWriter();
				pw.print(String.format("{\"saved\":%b}", saved));
				pw.flush();
			} catch (Exception e) {
				
			}
		
		} else if(cmd.equals("detail")) {
			String strNum = request.getParameter("num");
			BoardVO board = new BoardDAO().getBoard(strNum);
			request.setAttribute("board", board);
			viewPath = "/bbs/board_detail.jsp";
		
		} else if(cmd.equals("edit")) {
			String strNum = request.getParameter("num");
			BoardVO board = new BoardDAO().getBoard(strNum);
			request.setAttribute("board", board);
			viewPath = "/bbs/board_edit.jsp";
		
		
		} else if(cmd.equals("update")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			BoardVO board = new BoardVO();
			board.setNum(num);
			board.setTitle(title);
			board.setContents(contents);
			
			BoardDAO dao = new BoardDAO();
			boolean updated = dao.update(board);
			
			try {
				PrintWriter pw = response.getWriter();
				pw.printf("{\"updated\":%b}", updated);
				pw.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		} else if(cmd.equals("delete")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDAO dao = new BoardDAO();
			boolean deleted = dao.delete(num);
			
			try {
				PrintWriter pw = response.getWriter();
				pw.printf("{\"deleted\":%b}", deleted);
				pw.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return viewPath;
	}

}
