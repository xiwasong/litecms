package cn.hn.java.cms.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
	
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public void error() {
    }

    public String getErrorPath() {
        return PATH;
    }
}
