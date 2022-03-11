
package com.tjoeun.svc;

import javax.servlet.http.HttpServletRequest;

public class GugudanService {
	private HttpServletRequest req;
	
	public GugudanService(HttpServletRequest req){
		this.req = req;
	}
	
	public int getDan() {
		String sDan = req.getParameter("dan");
		int dan = 0;
		if(sDan == null || sDan.equals("")) {
			sDan = "2";
		}
		dan = Integer.valueOf(sDan);
		return dan;
	}
}
