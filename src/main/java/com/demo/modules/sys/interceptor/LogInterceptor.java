package com.demo.modules.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.modules.sys.model.User;

public class LogInterceptor implements HandlerInterceptor {

	public  static final String USER_SESSION = "user_session";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("拦截");
		HttpSession session = request.getSession();
		User u=(User) session.getAttribute(USER_SESSION);
		
		if(StringUtils.isEmpty(u)){
			System.out.println("nihao");
		response.sendRedirect("/jeesiteDemo/admin/login");
		return false;
		}
		System.out.println("拦截用户名 存在为 ："+u.getUsername());
		return true;
	}
	
}
