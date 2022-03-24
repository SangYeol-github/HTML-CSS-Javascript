package com.tjoeun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQL_JDBC {

	public static void main(String[] args) {
		
		MySQL_JDBC dao = new MySQL_JDBC();
		
		List<EmpVO> list = dao.getList();
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private List<EmpVO> getList() {
		this.conn = getConn();
		try {
			this.stmt = this.conn.createStatement();
			
			String sql = "SELECT * FROM emp";
			this.rs = stmt.executeQuery(sql);
			List<EmpVO> list = new ArrayList<>();
			
			while(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				
				EmpVO emp = new EmpVO();
				emp.setEmpno(empno);;
				emp.setEname(ename);
				emp.setDeptno(deptno);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
				list.add(emp);
			}
			closeAll();
			return list;
		} catch (Exception e) {
			
		}
		
		return null;
	}

	private void closeAll() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private Connection getConn() {
		try {
			System.out.println("Connecting to database...");
			
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "root", "tjoeun");
			return conn;
		} catch (Exception e) {
			System.err.println("DB !");
			e.printStackTrace();
		}
		return null;
	}
	
	public EmpVO getEmp(int empno) {
		this.conn = getConn();
		try {
			this.stmt = this.conn.createStatement();
			String sql = "SELECT * FROM emp WHERE empno=" + empno;
			this.rs = stmt.executeQuery(sql);
			EmpVO emp = null;
			
			if(rs.next()) {
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				
				emp = new EmpVO();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setDeptno(deptno);
				emp.setHiredate(hiredate);
				emp.setSal(sal);
			}
			closeAll();
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
