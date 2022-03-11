
package com.tjoeun.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.EmpDAO;
import com.tjoeun.svc.EmpService;
import com.tjoeun.vo.EmpVO;


@WebServlet("/emp") // demo/emp?cmd=list
public class EmpController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset = utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("EmpController 작동");
		
// 		웹브라우져 문자형식 셋팅
		request.setCharacterEncoding("utf-8");
		// 서비스 콤포넌트에게 파라미터
		// 요청이 list 라면 emp.txt 파일에서 데이터를 읽어 브라우저에 출력
		EmpService svc = new EmpService(request);
		String viewPath = svc.exec();
		getServletContext().getRequestDispatcher(viewPath).
		forward(request, response);
		
//		String cmd = svc.getCMD();
//		if(cmd.equals("list")) {
//			EmpDAO dao = new EmpDAO();
//			List<EmpVO> list = dao.getList();
//			request.setAttribute("list", list);
//			getServletContext().getRequestDispatcher("/empList.jsp").
//			forward(request, response);
//		}
		/* 이용자가 사원정보 리스트 페이지에서 특정 사원의 이름을 클릭하면
		 * 해당 사원의 상세 정보만 웹브라우저 화면에 표시되도록 한다. 
		 * demo/emp/?cmd=find&num=12*/
		
//		else if(cmd.equals("find")) {
//			String sNum = request.getParameter("num");
//			EmpDAO dao = new EmpDAO();
//			EmpVO emp = dao.find(sNum);
//			request.setAttribute("emp", emp);
//			getServletContext().getRequestDispatcher("/empDetail.jsp").
//			forward(request, response);
//		}
		// 이용자가 전송한 num,name,phone,email 정보를 수신하여
		// emp.txt 파일에 저장할 한 행으로 구성하고 파일에 저장한다
		
//		else if(cmd.equals("add")) {
//			String sNum = request.getParameter("num");
//			String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			String line = String.format("%s|%s|%s|%s",sNum,name,phone,email);
//			EmpDAO dao = new EmpDAO();
//			boolean saved = dao.addEmp(line);
//			request.setAttribute("saved", saved);
//			getServletContext().getRequestDispatcher("/addResult.jsp").forward(request, response);
//		}
//		else if(cmd.equals("edit")) {
//			String sNum = request.getParameter("num");
//			EmpDAO dao = new EmpDAO();
//			EmpVO emp = dao.find(sNum);
//			request.setAttribute("emp", emp);
//			getServletContext().getRequestDispatcher("/empEdit.jsp").
//			forward(request, response);
//		}
//		else if(cmd.equals("update")) {
//			String sNum = request.getParameter("num");
//			//String name = request.getParameter("name");
//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			//String line = String.format("%s|%s|%s|%s",sNum,name,phone,email);
//			//사원정보를 메모리에 로드하고 메모리에서 수정한 후 파일에 반영한다.
//			int keyNum = Integer.parseInt(sNum);
//			EmpDAO dao = new EmpDAO();
//			List<EmpVO> list = dao.getList();
//			for(int i=0; i<list.size(); i++) {
//				EmpVO emp = list.get(i);
//				if(emp.getNum()==keyNum) { // 수정대상 정보 찾기 
//					emp.setPhone(phone);
//					emp.setEmail(email);
//					break;
//				}
//			}
//				
//				boolean updated = new EmpDAO().overwrite(list);
//				request.setAttribute("updated", updated);
//				getServletContext().getRequestDispatcher("/updateResult.jsp").
//				forward(request,response);
//			 
//		} else if(cmd.equals("delete")) {
//			String sNum = request.getParameter("num");
//			int num = Integer.valueOf(sNum);
//			EmpDAO dao = new EmpDAO();
//			List<EmpVO> list = dao.getList();
//			for(int i=0; i<list.size(); i++) {
//				EmpVO emp = list.get(i);
//				if(emp.getNum()==num) {
//					list.remove(i);
//					break;
//				}
//			}
//			boolean deleted = dao.overwrite(list);
//			request.setAttribute("deleted", deleted);
//			getServletContext().getRequestDispatcher("/deleteResult.jsp").
//			forward(request,response);
//		}
//		
		
	}
		

}
