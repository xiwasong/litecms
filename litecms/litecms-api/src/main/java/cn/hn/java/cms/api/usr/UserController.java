package cn.hn.java.cms.api.usr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hn.java.cms.api.usr.bean.User;
import cn.hn.java.core.mvc.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/list")
	public List<User> getUsers(User u){
		return userService.getUsers(u);
	}
	
}
