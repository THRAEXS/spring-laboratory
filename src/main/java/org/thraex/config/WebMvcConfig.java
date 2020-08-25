package org.thraex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/08/11 17:35
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico", "/assets/**")
                .addResourceLocations("classpath:/templates/assets/");
        registry.addResourceHandler("/vue/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/vue/2.6.11/");
        registry.addResourceHandler("/AdminLTE/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/AdminLTE/3.0.5/");
        registry.addResourceHandler("/element-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/element-ui/2.13.2/");
        registry.addResourceHandler("/axios/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/axios/0.19.2/");
        registry.addResourceHandler("/qs/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/qs/6.9.4/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
        Stream.of("menu", "role", "user").forEach(it ->
                registry.addViewController(String.format("/%s", it)).setViewName(it));
    }

}
