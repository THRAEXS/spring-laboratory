package org.thraex.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thraex.base.properties.MvcProperties;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author 鬼王
 * @date 2020/08/11 17:35
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private Function<List<String>, String[]> toArray = s -> s.stream().toArray(String[]::new);

    @Autowired
    private MvcProperties properties;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        properties.getResources().stream().forEach(r ->
                registry.addResourceHandler(toArray.apply(r.getPaths()))
                        .addResourceLocations(toArray.apply(r.getLocations())));
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        properties.getViews().entrySet().stream().forEach(it -> registry
                .addViewController(String.format("/%s", it.getKey().replace("-", "/")))
                .setViewName(Optional.of(it.getValue()).filter(v -> !Strings.isBlank(v)).orElse(it.getKey())));
    }

}
