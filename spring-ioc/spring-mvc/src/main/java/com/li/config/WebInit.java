package com.li.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException { //tomcat启动时,先执行onStartup
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class); //注册
        context.setServletContext(servletContext); //servlet设置
        context.refresh(); //context 的重要方法 加载相应的Bean到容器中

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1); //启动顺序 启动后第一位被加载

//        FilterRegistration.Dynamic charEncodingFilter = servletContext.addFilter("charEncodingFilter", new CharacterEncodingFilter());
//        charEncodingFilter.setInitParameter("encoding","UTF-8");
//        charEncodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC),false,"/*");
    }

}
