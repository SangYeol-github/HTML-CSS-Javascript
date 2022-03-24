package com.tjoeun.svc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.PaginationDAO;
import com.tjoeun.util.PageUtil;
import com.tjoeun.vo.MemberVO;

public class PaginationService {
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	public PaginationService(HttpServletResponse response, HttpServletRequest request) {
		this.request = request;
		this.response = response;
	}

	public String exec() {
		String viewPath = null;
		
		String cmd = this.request.getParameter("cmd");
		if(cmd==null || cmd.equals("list")) {
			String sPage = request.getParameter("page");
			int page = sPage==null ? 1 : Integer.parseInt(sPage);  // 현재 페이지
			PaginationDAO dao = new PaginationDAO();
			List<Map> list = dao.getPageTotal(3, page); // 3개씩 특정 page 가져오기
			int totalCnt = (Integer)list.get(0).get("totalcnt");
//			System.out.println("총 행수:" + totalCnt);
			int totalPages = (int)Math.ceil(totalCnt/3.0);
//			System.out.println("총 페이지수 = " + totalCnt/3.0);
			System.out.printf("총행수 = %d, 총 페이지수 = %d\n", totalCnt, totalPages);
			
//			현재 페이지 7 이라면, 네비게이션 링크를 5개 보여주고자 할 경우,
//				5 6 7 8 9
			
			PageUtil pu = new PageUtil();
	         pu.setList(list);
	         pu.setRowsPerScreen(3); // 영향없음
	         pu.setCurrentPage(page);
	         pu.setTotalPages(totalPages);
	         pu.setTotalRows(totalCnt);
	         pu.setNavCount(5);
	         
	         request.setAttribute("pgutil", pu);
			/*
			int low = (page-2) < 1 ? 1 : page-2;
			int high = (low+4)>totalPages ? totalPages : low+4;
			low = high<=5 ? 1 : high-4;
			
			int[] links = new int[ (high-low) + 1 ];
			
			for(int n=low, i=0; n<=high; n++) {
				links[i++] = n;
			}
			
			request.setAttribute("totalcnt", totalCnt);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("links", links);
	        request.setAttribute("current", page);
			*/			
			request.setAttribute("list", list);
			viewPath = "/pagination/empList.jsp";
		
		} else if(cmd.equals("detail")) {
			PaginationDAO dao = new PaginationDAO();
			int empno = Integer.parseInt(request.getParameter("num"));
			MemberVO paging = dao.getMem(empno);
			request.setAttribute("paging", paging);
			viewPath = "/pagination/empDetail.jsp";
		
		} else if(cmd.equals("edit")) {
			PaginationDAO dao = new PaginationDAO();
			int empno = Integer.parseInt(request.getParameter("num"));
			MemberVO paging = dao.getMem(empno);
			request.setAttribute("paging", paging);
			viewPath = "/pagination/empEdit.jsp";
		
		} else if(cmd.equals("update")) {
			int empno = Integer.parseInt(request.getParameter("num"));
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			int sal = Integer.parseInt(request.getParameter("sal"));
			MemberVO paging = new MemberVO();
			paging.setEmpno(empno);
			paging.setDeptno(deptno);
			paging.setSal(sal);
			PaginationDAO dao = new PaginationDAO();
			boolean updated = dao.update(paging);
			request.setAttribute("updated", updated);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.printf("{\"updated\":%b}", updated);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} else if(cmd.equals("addForm")) {
			viewPath = "/pagination/empAddForm.jsp";
		
		} else if(cmd.equals("insert")) {
			int empno = Integer.parseInt(request.getParameter("num"));
			String ename = request.getParameter("ename");
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			String sHiredate = request.getParameter("hiredate");
			java.sql.Date hiredate = java.sql.Date.valueOf(sHiredate);
			int sal = Integer.parseInt(request.getParameter("sal"));
			
			MemberVO paging = new MemberVO();
			paging.setEmpno(empno);
			paging.setEname(ename);
			paging.setDeptno(deptno);
			paging.setHiredate(hiredate);
			paging.setSal(sal);
			
			PaginationDAO dao = new PaginationDAO();
			boolean inserted;
			try {
				inserted = dao.insert(paging);
				PrintWriter out = response.getWriter();
				out.printf("{\"inserted\":%b}", inserted);  
				out.flush();
			} catch (SQLIntegrityConstraintViolationException e1) {
				
				try {
					PrintWriter out = response.getWriter();
					out.print("{\"inserted\":false, \"cause\":\"PK 중복됨\"}");
					out.flush();
				} catch (IOException e) { }
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} else if(cmd.equals("delete")) {
			
			int empno = Integer.parseInt(request.getParameter("num"));
			PaginationDAO paging = new PaginationDAO();
			boolean deleted = paging.delete(empno);
			try {
				PrintWriter out = response.getWriter();
				out.printf("{\"deleted\":%b}", deleted);
				out.flush();
			} catch (IOException e) { }
		}
		return viewPath;
	}

}
