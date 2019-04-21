package org.third.spring.boot.hello.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//两种方式配置url映射到非默认路径（/static ， /public， /resources ，/META-INF/resources）
//1、方法一，修改application.properties配置文件
//例如，讲url为static的请求映射到static路径下
//
//spring.mvc.static-path-pattern=/static/** 
//spring.resources.static-locations=classpath:/static/
//
//    1
//    2
//
//2、方法二，继承WebMvcConfigurerAdapter，重载addResourceHandlers。
//
//例如：
//@Component
//public class MyResHandler extends WebMvcConfigurerAdapter {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }
//}
@Component
public class WebMvcMappingConfig extends WebMvcConfigurerAdapter{
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/static/ui/");
       super.addResourceHandlers(registry);
   }
}
