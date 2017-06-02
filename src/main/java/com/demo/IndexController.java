package com.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.modules.sys.interceptor.LogInterceptor;
import com.demo.modules.sys.model.User;

@Controller
@RequestMapping("/admin")
public class IndexController {
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute(LogInterceptor.USER_SESSION);
		model.addAttribute("name", user.getUsername());
		return "index";
	}
	@RequestMapping("/")
	@ResponseBody
	public String a(){
		return "a";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		System.err.println("登陆");
		return "login";
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public void logout(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
		HttpSession session = request.getSession();
		session.removeAttribute(LogInterceptor.USER_SESSION);
		 reponse.sendRedirect("/jeesiteDemo/admin/login");
	
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void loginValide(User user,HttpServletRequest request,Model model,HttpServletResponse reponse) throws IOException{
		System.err.println("登陆验证");
		System.out.println("username : "+user.getUsername());
		//验证正确
		//存放session
		
		HttpSession session = request.getSession();
		session.putValue(LogInterceptor.USER_SESSION, user);
		model.addAttribute("name", user.getUsername());
		
		reponse.sendRedirect("/jeesiteDemo/admin/index");
	}
}
