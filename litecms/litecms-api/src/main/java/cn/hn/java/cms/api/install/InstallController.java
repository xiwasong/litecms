package cn.hn.java.cms.api.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hn.java.core.mvc.WebContext;

@Controller
@RequestMapping("/install")
public class InstallController {
	
	@Autowired
	InstallService installService;

	@RequestMapping("/index")
	public void index(){
	}
	
	@RequestMapping("/installDb")
	public void installDb(){
		installService.createTables();
		WebContext.installed=true;
	}
}
