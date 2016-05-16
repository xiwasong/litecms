package cn.hn.java.cms.api;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

public class TomcatEmbeddedFactory extends TomcatEmbeddedServletContainerFactory{

	private String docBase;
	
	public TomcatEmbeddedFactory(String docBase){
		this.docBase=docBase;
	}
	
	@Override
	protected void postProcessContext(Context context) {
		context.setDocBase(docBase);
	}
	
}
