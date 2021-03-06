package com.example.sso.cross.domain.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class UrlConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        List<MediaType> supportedMediaTypes = new ArrayList<>(4);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(this.fastJsonHttpMessageConverters());
    }
    @Bean
    public FreeMarkerViewResolver getFreeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();

        freeMarkerViewResolver.setOrder(0);
        freeMarkerViewResolver.setSuffix(".html");
        freeMarkerViewResolver.setCache(false);
        freeMarkerViewResolver.setRequestContextAttribute("request");
        freeMarkerViewResolver.setContentType("text/html;charset=utf-8");
        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);

        return freeMarkerViewResolver;
    }
    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");

        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "5");
        settings.setProperty("url_escaping_charset", "UTF-8");
        settings.setProperty("defaultEncoding", "UTF-8");
        settings.setProperty("whitespace_stripping", "true");
        settings.setProperty("boolean_format", "true,false");
        settings.setProperty("number_format", "0.##########");
        settings.setProperty("locale", "zh_CN");
        settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.setProperty("date_format", "yyyy-MM-dd");
        settings.setProperty("time_format", "HH:mm:ss");
        settings.setProperty("tag_syntax", "square_bracket");
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_exception_handler", "ignore");
        settings.setProperty("auto_import", "/spring.ftl as spring, /common/common.ftl as common");
        freeMarkerConfigurer.setFreemarkerSettings(settings);

        return freeMarkerConfigurer;
    }
}


