package org.thraex.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.Collections;
import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/01 08:47
 */
@ConfigurationProperties("thraex.mvc")
public class MvcConfigProperties {

    /**
     * Resource list. Reference {@link ResourceHandlerRegistry}
     */
    private List<Resource> resources = Collections.emptyList();

    /**
     * View controllers. Reference {@link ViewControllerRegistry}
     */
    private List<String> views = Collections.emptyList();

    public static class Resource {

        private List<String> paths = Collections.emptyList();

        private List<String> locations = Collections.emptyList();

        public List<String> getPaths() {
            return paths;
        }

        public Resource setPaths(List<String> paths) {
            this.paths = paths;
            return this;
        }

        public List<String> getLocations() {
            return locations;
        }

        public void setLocations(List<String> locations) {
            this.locations = locations;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "paths=" + paths +
                    ", locations=" + locations +
                    '}';
        }
    }

    public List<Resource> getResources() {
        return resources;
    }

    public MvcConfigProperties setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public List<String> getViews() {
        return views;
    }

    public MvcConfigProperties setViews(List<String> views) {
        this.views = views;
        return this;
    }

    @Override
    public String toString() {
        return "MvcConfigProperties{" +
                "resources=" + resources +
                ", views=" + views +
                '}';
    }

}
