
package com.tjoeun.model;
public class GugudanModel1 {
	// DAO(Data Access Object 데이터 입출력 기능 객체), VO(Value Object 데이터 그 자체)
	public String getGugudan(int dan) {
		
		String sGugu = "";
		for(int i = 1; i <= 9; i++) {
			sGugu += String.format("%d * %d = %d<br>", dan, i, dan * i);
		}
		return sGugu;
	}

}
