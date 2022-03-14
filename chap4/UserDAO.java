package com.tjoeun.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.UserVO;

public class UserDAO {
	
	private static final String fname = "C:/test/members.dat";
	
	public boolean login(UserVO user) {
		
		List<UserVO> list = load();
		
		for(int i=0; i<list.size(); i++) {
			UserVO u = list.get(i);
			if(u.equals(user)) {
				System.out.printf("%d, %d\n", user.hashCode(),u.hashCode());
				return true;
			}
		}
		return false;
	}
	
	private List<UserVO> load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			List<UserVO> list = new ArrayList<UserVO>();
			
			while((line=br.readLine())!=null) {
				String[] token = line.split(" ");
				UserVO user = new UserVO(token[0], token[1]);
				list.add(user);
			}
			br.close();
			return list;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
}
