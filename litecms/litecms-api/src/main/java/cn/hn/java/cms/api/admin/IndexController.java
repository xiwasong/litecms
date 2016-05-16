package cn.hn.java.cms.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hn.java.cms.api.admin.user.bean.User;
import cn.hn.java.cms.api.constants.Constant;
import cn.hn.java.core.mvc.BaseController;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController{


	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login(){
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void postLogin(User u){
		this.addSessionAttribute(Constant.USER_SESSION_KEY, u);
		redirectTo("/admin/index.html");
	}
	
	@RequestMapping("/index")
	public void index(){
	}
	
	@RequestMapping("/welcome")
	public void welcome(){
	}
	
	
}
