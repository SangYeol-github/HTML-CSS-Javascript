package com.tjoeun.vo;

import java.util.Objects;

public class UserVO {
	
	private String uid;
	private String pwd;
	
	public UserVO(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserVO u = (UserVO)obj;
		if(uid.equals(u.getUid()) && pwd.equals(u.getPwd())) {
			return true;
		} else {			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(this.uid, this.pwd);
	}
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
