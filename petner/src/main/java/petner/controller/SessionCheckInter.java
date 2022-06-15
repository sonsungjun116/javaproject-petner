package petner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInter extends HandlerInterceptorAdapter {
	// 구현메소드를 상속받은 경우로 자신이 필요한 형태만 오버라이딩 하면된다.
	// 만약 인터페이스로 상속받는다면 세가지 모두 오버라이딩 해야한다.
	// preHandle(request,response,handler)메소드
	// 1.Controller에서 요청(*.nhn)을 받기 전에  preHandle()가 호출되어 가로채는 역할로 사용
	// 2.로그인 하지않고(세션이 없으면) 요청하면 로그인 폼으로 이동 하도록 해주는 역할
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("mem_id");
		if (id == null || id.equals(""))  {		
			response.sendRedirect("Loginform");	// 세션이 없으면 로그인 폼으로 이동
			return false;
		}
		return true;
	}
}