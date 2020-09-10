package org.agriculture.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.agriculture.base.properties.MvcProperties;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author MLeo
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
        final Map<String, String> views = properties.getViews();
        views.keySet().stream().forEach(k -> registry
                .addViewController(String.format("/%s", k.replace("-", "/")))
                .setViewName(Optional.of(views.get(k)).filter(v -> !Strings.isBlank(v)).orElse(k)));
    }

}
