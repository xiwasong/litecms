package cn.hn.java.cms.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import cn.hn.java.core.mvc.ControllerAspectTool;


/**
 * 拦截以Controller结尾控制器的所有方法
 * 记录日志、保存错误信息
 * @author songjiangang
 * @time 2016年4月25日 下午9:27:22
 */
@Aspect
@Component
public class ControllerAspect extends WebApplicationObjectSupport{
    
	/**
	 * 拦截action执行返回结果
	 */
    @Pointcut("execution(public * cn.hn.java.cms..*.*Controller.*(..))")
    public void catchActionExecuteResult(){}

	/**
	 * 拦截action执行返回结果
	 */
    @Around("catchActionExecuteResult()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
    	return ControllerAspectTool.around(pjp, getApplicationContext());
    }
}
