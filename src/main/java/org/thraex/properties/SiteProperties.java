package org.thraex.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 鬼王
 * @date 2020/08/19 09:24
 */
@Data
@ConfigurationProperties("thraex.site")
public class SiteProperties {

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

}
