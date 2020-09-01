package org.thraex.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
     * View page for index
     */
    private String index = ADMIN;

    /**
     * Admin dashboard
     */
    private String dashboard = "/dashboard";

    public boolean isAdmin() {
        return ADMIN.equalsIgnoreCase(index);
    }

    public boolean notAdmin() {
        return !isAdmin();
    }

}
