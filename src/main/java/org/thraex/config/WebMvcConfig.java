package org.thraex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thraex.base.properties.MvcConfigProperties;

import java.util.List;
import java.util.function.Function;

/**
 * @author 鬼王
 * @date 2020/08/11 17:35
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private Function<List<String>, String[]> toArray = s -> s.stream().toArray(String[]::new);

    @Autowired
    private MvcConfigProperties properties;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        properties.getResources().parallelStream().forEach(r ->
                registry.addResourceHandler(toArray.apply(r.getPaths()))
                        .addResourceLocations(toArray.apply(r.getLocations())));
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        properties.getViews().parallelStream().forEach(it ->
                registry.addViewController(String.format("/%s", it)).setViewName(it));
    }

}
