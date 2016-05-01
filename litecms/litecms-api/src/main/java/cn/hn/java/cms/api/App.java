package cn.hn.java.cms.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.rythmengine.spring.web.RythmHolder;
import org.rythmengine.spring.web.RythmViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.hn.java.core.db.SnJdbcTemplate;
import cn.hn.java.core.db.multiple.MultipleDataSource;
import cn.hn.java.core.db.paging.MysqlPagingConverter;
import cn.hn.java.core.mvc.RythmViewHolder;
import cn.hn.java.core.mvc.SnJsonMaper;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages="cn.hn.java")
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class App extends SpringBootServletInitializer
{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	
    public static void main( String[] args ) throws URISyntaxException, IOException
    {
    	
    	SpringApplication.run(App.class, args);
    	
//    	
//
//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }
    
    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource() {
        return new BasicDataSource();
    }
 
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public CacheManager cacheManager() {
    	SimpleCacheManager cache=new SimpleCacheManager();
    	List<Cache> caches=new ArrayList<Cache>();
    	caches.add(new ConcurrentMapCache("mainCache", true));
    	cache.setCaches(caches);
    	return cache;
    }
    
    @Bean
    public MultipleDataSource multipleDataSource(){
    	MultipleDataSource dataSource=new MultipleDataSource();
    	List<SnJdbcTemplate> templates=new ArrayList<SnJdbcTemplate>();
    	SnJdbcTemplate template=new SnJdbcTemplate();
    	template.setDataSource(dataSource());
    	template.setPagingConverter(new MysqlPagingConverter());
    	templates.add(template);
    	dataSource.setJdbcTemplates(templates);
    	return dataSource;
    }
    
    @Primary
    @Bean
    public ContentNegotiatingViewResolver viewResolver(){
    	ContentNegotiatingViewResolver viewResolver=new ContentNegotiatingViewResolver();
    	
    	//设置页面视图解析器
    	RythmViewResolver razorResolver=new RythmViewResolver();
    	//视图模板存放目录
    	razorResolver.setPrefix("/views/");
    	razorResolver.setContentType("text/html;charset=UTF-8");
    	razorResolver.setExposeContextBeansAsAttributes(true);
    	razorResolver.setExposePathVariables(true);
    	razorResolver.setExposeRequestAttributes(true);
    	razorResolver.setExposeSessionAttributes(true);
    	razorResolver.setExposeSpringMacroHelpers(true);
    	List<ViewResolver> viewResolvers=new ArrayList<ViewResolver>();
    	viewResolvers.add(razorResolver);
    	viewResolver.setViewResolvers(viewResolvers);
    	
    	List<View> defaultViews=new ArrayList<View>();
    	MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
    	jsonView.setObjectMapper(new SnJsonMaper());
    	defaultViews.add(jsonView);
    	
    	viewResolver.setDefaultViews(defaultViews);
    	return viewResolver;
    }
    
    @Bean
    public RythmHolder rythmHolder(
		@Value("${template.home}") final String templateHome,
		@Value("${template.mode}") final String mode
    ){
    	return new RythmViewHolder(templateHome, mode);
    }
}