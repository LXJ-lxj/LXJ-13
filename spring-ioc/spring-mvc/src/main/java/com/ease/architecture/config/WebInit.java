package com.ease.architecture.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();//上下文
        context.register(WebConfig.class);//注册这个类
        context.setServletContext(servletContext);
        context.refresh();//加载相应的bean到容器中

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));//添加servlet  s 为名称，new的是一个类
        dispatcher.addMapping("/");//对servlet进行配置，设置mapping
        dispatcher.setLoadOnStartup(1);//启动顺序，servlet设置为第一个被加载的

//        FilterRegistration.Dynamic charEncodingFilter = servletContext.addFilter("charEncodingFilter", new CharacterEncodingFilter());
//        charEncodingFilter.setInitParameter("encoding", "UTF-8");
//        charEncodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), false, "/*");
    }
}
