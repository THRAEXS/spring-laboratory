package org.thraex.leo.config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/10/15 15:06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Config extends Entity<Config> {

    /**
     * spring.cloud.config.name
     */
    private String name;

    /**
     * spring.cloud.config.profile
     */
    private String profile;

    /**
     * spring.cloud.config.label
     */
    private String label;

    private String key;

    private String value;

}
