
package com.tjoeun.svc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tjoeun.dao.EmpDAO;
import com.tjoeun.vo.EmpVO;

public class EmpService {
	
	private HttpServletRequest request;
	
	public EmpService(HttpServletRequest req) {
		this.request = req;
	}
	
	public String getCMD() {
		return this.request.getParameter("cmd");
	}

	public String exec() {
		String cmd = getCMD();
		if(cmd==null||cmd.equals("list")) {
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.getList();
			request.setAttribute("list", list);
			return "/empList.jsp";
		} else if(cmd.equals("find")) {
			String sNum = request.getParameter("num");
			EmpDAO dao = new EmpDAO();
			EmpVO emp = dao.find(sNum);
			request.setAttribute("emp", emp);
			return "/empDetail.jsp";
		} else if(cmd.equals("add")) {
			String sNum = request.getParameter("num");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String line = String.format("%s|%s|%s|%s",sNum,name,phone,email);
			EmpDAO dao = new EmpDAO();
			boolean saved = dao.addEmp(line);
			request.setAttribute("saved", saved);
			return "/addResult.jsp";
			}
			else if(cmd.equals("edit")) {
				String sNum = request.getParameter("num");
				EmpDAO dao = new EmpDAO();
				EmpVO emp = dao.find(sNum);
				request.setAttribute("emp", emp);
				return "/empEdit.jsp";
			}
			else if(cmd.equals("update")) {
				String sNum = request.getParameter("num");
				//String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				//String line = String.format("%s|%s|%s|%s",sNum,name,phone,email);
				//사원정보를 메모리에 로드하고 메모리에서 수정한 후 파일에 반영한다.
				int keyNum = Integer.parseInt(sNum);
				EmpDAO dao = new EmpDAO();
				List<EmpVO> list = dao.getList();
				for(int i=0; i<list.size(); i++) {
					EmpVO emp = list.get(i);
					if(emp.getNum()==keyNum) { // 수정대상 정보 찾기 
						emp.setPhone(phone);
						emp.setEmail(email);
						break;
					}
				}
					
					boolean updated = new EmpDAO().overwrite(list);
					request.setAttribute("updated", updated);
					return "/updateResult.jsp";
				 
			} else if(cmd.equals("delete")) {
				String sNum = request.getParameter("num");
				int num = Integer.valueOf(sNum);
				EmpDAO dao = new EmpDAO();
				List<EmpVO> list = dao.getList();
				for(int i=0; i<list.size(); i++) {
					EmpVO emp = list.get(i);
					if(emp.getNum()==num) {
						list.remove(i);
						break;
					}
				}
				boolean deleted = dao.overwrite(list);
				request.setAttribute("deleted", deleted);
				return "/deleteResult.jsp";
			}
		
			 return null;
	}
}
