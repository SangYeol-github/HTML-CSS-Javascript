package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.MemberVO;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<MemberVO> getList() {
		
		this.conn = getConn();
		try {
			String sql = "SELECT * FROM emp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<MemberVO> list = new ArrayList<>();
			
			while(rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				
				MemberVO mem = new MemberVO();
				mem.setEmpno(empno);
				mem.setEname(ename);
				mem.setDeptno(deptno);
				mem.setHiredate(hiredate);
				mem.setSal(sal);
				list.add(mem);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeAll();
		}
		
		return null;
	}

	private void closeAll() {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(pstmt !=null) {
				pstmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private Connection getConn() {
		
		try {
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

	public MemberVO getMem(int empno) {
		this.conn = getConn();
		
		try {
			String sql = "SELECT * FROM emp WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			MemberVO mem = null;
			
			if(rs.next()) {
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				
				mem = new MemberVO();
				mem.setEmpno(empno);
				mem.setEname(ename);
				mem.setDeptno(deptno);
				mem.setHiredate(hiredate);
				mem.setSal(sal);
			}
			return mem;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeAll();
		}
		return null;
	}

	public boolean update(MemberVO mem) {
		this.conn = getConn();
		String sql = "UPDATE emp SET deptno=?, sal=? WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getDeptno());
			pstmt.setInt(2, mem.getSal());
			pstmt.setInt(3, mem.getEmpno());
			
			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;
			
		} catch (SQLException e) {
			
		} finally {
			closeAll();
		}
		return false;
	}

	public boolean insert(MemberVO mem) 
			throws java.sql.SQLIntegrityConstraintViolationException {
		conn = getConn();
		String sql = "INSERT INTO emp VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getEmpno());
			pstmt.setString(2, mem.getEname());
			pstmt.setDate(3, mem.getHiredate());
			pstmt.setInt(4, mem.getDeptno());
			pstmt.setInt(5, mem.getSal());
			
			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			if(e instanceof java.sql.SQLIntegrityConstraintViolationException) {
				throw (java.sql.SQLIntegrityConstraintViolationException) e;
			}
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	public boolean delete(int empno) {
		conn = getConn();
		
		String sql = "DELETE FROM emp WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int rows = pstmt.executeUpdate();
			return rows >0 ? true : false;
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			closeAll();
		}
		
		return false;
	}

}
