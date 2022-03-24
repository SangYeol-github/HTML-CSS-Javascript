package com.tjoeun.svc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.dao.MemberDAO;
import com.tjoeun.vo.MemberVO;

public class MemberService {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public MemberService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String exec() {
		
		String viewPath = null;
		
		String cmd = request.getParameter("cmd");
		if(cmd==null || cmd.equals("list")) {
			MemberDAO dao = new MemberDAO();
			List<MemberVO> list = dao.getList();
			request.setAttribute("list", list);
			viewPath = "/mem/memberList.jsp";
		
		} else if(cmd.equals("detail")) {
			MemberDAO dao = new MemberDAO();
			int empno = Integer.parseInt(request.getParameter("num"));
			MemberVO mem = dao.getMem(empno);
			request.setAttribute("mem", mem);
			viewPath = "/mem/memDetail.jsp";
		
		} else if(cmd.equals("edit")) {
			MemberDAO dao = new MemberDAO();
			int empno = Integer.parseInt(request.getParameter("num"));
			MemberVO mem = dao.getMem(empno);
			request.setAttribute("mem", mem);
			viewPath = "/mem/memEdit.jsp";
		
		} else if(cmd.equals("update")) {
			MemberDAO dao = new MemberDAO();
			int empno = Integer.parseInt(request.getParameter("num"));
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			int sal = Integer.parseInt(request.getParameter("sal"));
			
			MemberVO mem = new MemberVO();
			mem.setEmpno(empno);
			mem.setDeptno(deptno);
			mem.setSal(sal);
			
			
			boolean updated = dao.update(mem);
			request.setAttribute("updated", updated);
			
			try {
				PrintWriter pw = response.getWriter();
				pw.printf("{\"updated\":%b}", updated);
				pw.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} else if(cmd.equals("addForm")) {
			viewPath = "/mem/memAddForm.jsp";
			
		} else if(cmd.equals("insert")) {
			
			int empno = Integer.parseInt(request.getParameter("num"));
			String ename = request.getParameter("ename");
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			String sHiredate = request.getParameter("hiredate");
			java.sql.Date hiredate =  java.sql.Date.valueOf(sHiredate);			
			int sal = Integer.parseInt(request.getParameter("sal"));
			
			MemberVO mem = new MemberVO();
			mem.setEmpno(empno);
			mem.setEname(ename);
			mem.setDeptno(deptno);
			mem.setHiredate(hiredate);
			mem.setSal(sal);
			
			MemberDAO dao = new MemberDAO();
			boolean inserted;
			try {
				
				inserted = dao.insert(mem);
				PrintWriter pw = response.getWriter();
				pw.printf("{\"inserted\":%b}", inserted);
				pw.flush();
			} catch (SQLIntegrityConstraintViolationException e1) {
				try {
					PrintWriter pw = response.getWriter();
					pw.print("{\"inserted\":false, \"cause\":\"PK 중복됨\"}");
					pw.flush();
				} catch (IOException e) {
					// TODO: handle exception
				}
			} catch (IOException e) {
				
			}
		
		} else if(cmd.equals("delete")) {
			int empno = Integer.parseInt(request.getParameter("num"));
			MemberDAO dao = new MemberDAO();
			boolean deleted = dao.delete(empno);
			
			try {
				PrintWriter pw = response.getWriter();
				pw.printf("{\"deleted\":%b}", deleted);
				pw.flush();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		return viewPath;
	}

}
