
package com.tjoeun.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.EmpVO;

public class EmpDAO {
	
	private static final String fname = "C:/test/emp.txt";
	// emp.txt 파일에서 한행한행을 읽어와서 EmpVO에 저장하여 그 List리턴
	public List<EmpVO> getList() {
		
		try {// 파일에서 한행한행 읽어올수있는 스트림을 준비했다는 코드
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			List<EmpVO> list = new ArrayList<EmpVO>();
			while((line = br.readLine()) != null) {
				// 구분자가 특수문자라면 앞에다 역슬래쉬 두개를 넣어준다.
				String[] token = line.split("\\|");
				EmpVO emp = new EmpVO();
				emp.setNum(Integer.valueOf(token[0]));
				emp.setName(token[1]);
				emp.setPhone(token[2]);
				emp.setEmail(token[3]);
				list.add(emp);
			}
			br.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public EmpVO find(String sNum) {
		// 파일을 열고 해당 번호의 사원정보를 찾아 EmpVO로 로드하고, 참조를 리턴한다.
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			while((line = br.readLine()) != null) {
				// 구분자가 특수문자라면 앞에다 역슬래쉬 두개를 넣어준다.
				String[] token = line.split("\\|");
				if(token[0].equals(sNum)) {
					EmpVO emp = new EmpVO();
					emp.setNum(Integer.valueOf(token[0]));
					emp.setName(token[1]);
					emp.setPhone(token[2]);
					emp.setEmail(token[3]);
					br.close();
					return emp;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public boolean addEmp(String line) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true)); //true 이어쓰겠다.
			pw.println(line);
			pw.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean overwrite(List<EmpVO> list) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname));
			for(int i=0; i<list.size(); i++) {
				EmpVO emp = list.get(i);
				pw.println(emp.getStr());
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
