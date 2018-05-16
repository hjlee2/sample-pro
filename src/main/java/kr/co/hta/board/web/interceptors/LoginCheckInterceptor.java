package kr.co.hta.board.web.interceptors;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {

	private Set<String> urls;
	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("preHandle() 실행됨");
	
		String requestURI = request.getRequestURI();
//		System.out.println(requestURI);
		if (urls.contains(requestURI)) {
			return true;
		}
		
		HttpSession session = request.getSession();
		if (session.getAttribute("LOGIN_USER") !=null) {
			return true;
		}
		
		response.sendRedirect("/user/login.do?err=deny");
		
		return false;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle() 실행됨");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
