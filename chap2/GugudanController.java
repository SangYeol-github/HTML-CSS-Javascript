
package com.tjoeun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjoeun.model.GugudanModel1;
import com.tjoeun.svc.GugudanService;

@WebServlet("/gugu")
public class GugudanController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.요청 접수 -> 서비스 콤포넌트에게 요청분석 의뢰, GugudanService
		// 2.GuguModel1에게 구구단 완성 의뢰
		// 3.View에게 구구단 출력 의뢰, GugudanView.jsp
		
		// 1. 요청분석
		GugudanService svc = new GugudanService(request);
		int dan = svc.getDan();
//		System.out.printf("전달된 단수 = %d\n", dan);
		
		// 2. 응답작성
		GugudanModel1 gm = new GugudanModel1();
		String sGugu = gm.getGugudan(dan);
//		System.out.println(sGugu);
		
		// 3. 응답출력 guguView.jsp에게 완성된 구구단 출력의뢰.
//		HttpSession session = request.getSession(); // 세션영역을 객체를 관리하는것을 세션이다.
		// 세션영역에 g라는 이름으로 구구단을 저장하겠다는 메소드 : setAttribute
//		session.setAttribute("g", sGugu);
		// 리퀘스트영역에 g라는 이름으로 구구단을 저장하겠다는 메소드 : setAttribute
		request.setAttribute("g", sGugu);
		// jsp에 request, response를 forward메소드를 사용하여 전달한다.
		getServletContext().getRequestDispatcher("/guguView.jsp").forward(request, response); 
	}

}
