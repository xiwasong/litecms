package cn.hn.java.cms.api;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.hn.java.cms.api.admin.user.bean.User;
import cn.hn.java.cms.api.constants.Constant;
import cn.hn.java.core.mvc.WebContext;

@Component
public class ActionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);	
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//为上下文准备response
		WebContext.preperedResponse(response);
//		System.out.println(request.getServletContext().getRealPath("/"));
		
		String root=request.getContextPath();
		String url=request.getRequestURI();

		//默认未安装
		if(!WebContext.installed){
			//检查是不是存在安装目录
			String installPath=request.getSession().getServletContext().getRealPath("/install");
			WebContext.installed=!new File(installPath).exists();
			if(!WebContext.installed){
				//需要安装,跳转
				if(!url.startsWith(root+"/install") && new File(installPath).exists()){
					WebContext.redirectTo("/install/index.html");
				}
			}
		}
		
		//登录检测
		if((
				url.startsWith(root+"/admin") && 
				WebContext.getSessionCookie(Constant.USER_SESSION_KEY,User.class)==null
			) &&
			!url.startsWith(root+"/admin/login")
		){
			WebContext.redirectTo("/admin/login.html");
		}
		
		return super.preHandle(request, response, handler);
	}
}
