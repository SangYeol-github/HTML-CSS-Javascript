
package com.tjoeun.vo;

public class EmpVO {
	private int num;
	private String name;
	private String phone;
	private String email;
	
	public String getStr() {
		return String.format("%d|%s|%s|%s",num,name,phone,email);
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
