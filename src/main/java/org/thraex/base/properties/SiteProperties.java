package org.thraex.base.properties;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 鬼王
 * @date 2020/08/19 09:24
 */
@Data
@ConfigurationProperties("thraex.site")
public class SiteProperties {

    /**
     * Whether to enable portal mode
     */
    private boolean enabled;

    private Admin admin = new Admin();

    private Portal portal = new Portal();

    /**
     * Reference {@link AbstractRequestMatcherRegistry#antMatchers(String...)}
     */
    private Set<String> permits = new HashSet<>();

    public Mode mode() {
        return enabled ? portal : admin;
    }

    public String index() {
        return mode().view();
    }

    public String redirect() {
        return mode().redirect();
    }

    public String[] permits() {
        permits.add(mode().permit());
        return permits.stream().filter(p -> Strings.isNotBlank(p)).toArray(String[]::new);
    }

    public interface Mode {

        /**
         * Root(<code>/</code>) view
         *
         * @return {@link String} Default: <code>admin.html</code> or <code>index.html</code>
         */
        String view();

        /**
         * Specifies where users will be redirected after authenticating successfully
         *
         * @return {@link String} Default: <code>/</code> or <code>/admin</code>
         */
        String redirect();

        /**
         * Reference {@link AbstractRequestMatcherRegistry#antMatchers(String...)}
         * @return {@link String}
         */
        String permit();

    }

    @Data
    public static class Admin implements Mode {

        /**
         * Site title
         */
        private String title = "THRAEX | Admin";

        /**
         * Logo path
         */
        private String logo = "/AdminLTE/dist/img/AdminLTELogo.png";

        /**
         * Banner text behind the logo
         */
        private String brand = "THRAEX";

        /**
         * Default: admin.html
         */
        private String view = "admin";

        /**
         * Specifies where users will be redirected after authenticating successfully
         */
        private String redirect = "/";

        @Override
        public String view() {
            return view;
        }

        @Override
        public String redirect() {
            return redirect;
        }

        @Override
        public String permit() {
            return null;
        }

    }

    @Data
    public static class Portal implements Mode {

        private String view = "index";

        private String redirect = "/admin";

        @Override
        public String view() {
            return view;
        }

        @Override
        public String redirect() {
            return redirect;
        }

        @Override
        public String permit() {
            return "/";
        }

    }

}
