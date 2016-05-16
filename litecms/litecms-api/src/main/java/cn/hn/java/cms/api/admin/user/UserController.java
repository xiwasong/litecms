package cn.hn.java.cms.api.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hn.java.cms.api.admin.user.bean.User;
import cn.hn.java.core.mvc.BaseController;

@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/list")
	public List<User> getUsers(User u){
		return userService.getUsers(u);
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public void addUser(User u){
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public void postAddUser(User u){
	}
}
