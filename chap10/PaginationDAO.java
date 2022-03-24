package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tjoeun.vo.MemberVO;

public class PaginationDAO
{
	private Connection conn; //연결정보
	//private Statement stmt;
	private PreparedStatement pstmt; //전달
	private ResultSet rs;

	public MemberVO getMem(int empno) 
	{
		this.conn = getConn();
		try {
			//stmt = conn.createStatement();
			
			String sql = "SELECT * FROM emp WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);  //실제값을 전달. Int로.

			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery(); //실행

			MemberVO mem = null;

			if (rs.next()) {
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
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}

	public List<MemberVO> getList() 
	{
		this.conn = getConn();
		try {
			//stmt = conn.createStatement();
			
			String sql = "SELECT * FROM emp";
			pstmt = conn.prepareStatement(sql);

			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();

			List<MemberVO> list = new ArrayList<>();

			while (rs.next()) {
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
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public List<MemberVO> getPage(int itmes, int page) {
		this.conn = getConn();
		try {
			//stmt = conn.createStatement();
			
			String sql = "SELECT * FROM " +
					"(SELECT * FROM emp LIMIT ?, ?) t1 " +
					"CROSS JOIN " +
					"(SELECT COUNT(*) totalcnt FROM emp) t2";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*itmes);
			pstmt.setInt(2, itmes);

			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();

			List<MemberVO> list = new ArrayList<>();

			while (rs.next()) {
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
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	
	}
	
	public List<Map> getPageTotal(int itmes, int page) {
		this.conn = getConn();
		try {
			//stmt = conn.createStatement();
			
			String sql = "SELECT * FROM " +
					"(SELECT * FROM emp LIMIT ?, ?) t1 " +
					"CROSS JOIN " +
					"(SELECT COUNT(*) totalcnt FROM emp) t2";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*itmes);
			pstmt.setInt(2, itmes);

			//rs = stmt.executeQuery(sql);
			rs = pstmt.executeQuery();

			List<Map> list = new ArrayList<>();

			while (rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int totalCnt = rs.getInt("TOTALCNT");
				//System.out.println("총 행수 " + totalCnt);
				
				HashMap<String, Object> map = new HashMap<>();
				
				map.put("empno", empno);
				map.put("ename", ename);
				map.put("deptno", deptno);
				map.put("hiredate", hiredate);
				map.put("sal", sal);
				map.put("totalcnt", totalCnt);
				list.add(map);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	
	}
	
	public boolean update(MemberVO mem) 
	{
		this.conn = getConn();
		String sql = "UPDATE emp SET deptno=?, sal=? WHERE empno=?"; 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getDeptno());
			pstmt.setInt(2, mem.getSal());
			pstmt.setInt(3, mem.getEmpno());
			
			int rows = pstmt.executeUpdate();
			return rows>0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public boolean insert(MemberVO mem) 
			throws java.sql.SQLIntegrityConstraintViolationException
	{
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
			return rows>0 ? true : false;
		} 
		catch (SQLException e) 
		{
			if(e instanceof java.sql.SQLIntegrityConstraintViolationException){
				throw (java.sql.SQLIntegrityConstraintViolationException) e;
			}
			e.printStackTrace();
		}
		finally {
			closeAll();
		}
		return false;
	}
	
	public boolean delete(int empno) 
	{
		
		conn = getConn();
		
		String sql = "DELETE FROM emp WHERE empno=?";
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int rows = pstmt.executeUpdate();
			
			return rows>0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return false;
	}
	
	private Connection getConn() 
	{
		try {
			//System.out.println("Connecting to database...");

			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver"); //연결패키지
			conn = DriverManager.getConnection(url, "root", "tjoeun");
			return conn;
		} catch (Exception ex) {
			System.err.println("DB !");
			ex.printStackTrace();
		}
		return null;
	}
	
	private void closeAll() 
	{
		try { //연결된 역순으로.
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
