package org.thraex.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/08/19 09:24
 */
@Data
@ConfigurationProperties("thraex.site")
public class SiteProperties {

    public static final String ADMIN = "admin";

    /**
     * Site title
     */
    private String title;

    /**
     * Logo path
     */
    private String logo;

    /**
     * Banner text behind the logo
     */
    private String brand;

    /**
     * View page for index. Optional value: [admin | index]
     */
    private String index = ADMIN;

    /**
     * Admin dashboard
     */
    private String dashboard = "/dashboard";

    public boolean isAdmin() {
        return ADMIN.equalsIgnoreCase(index);
    }

    public String[] permit() {
        return Stream.of(isAdmin() ? null : "/").filter(Objects::nonNull).toArray(String[]::new);
    }

    public String successUrl() {
        return isAdmin() ? "/" : "/admin";
    }

    public String index() {
        return getIndex();
    }

    public String getIndex() {
        return index.toLowerCase();
    }

}
