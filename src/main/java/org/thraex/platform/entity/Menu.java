package org.thraex.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/08/19 10:21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends Entity<Menu> {

    private String name;

    private String code;

    private String url;

    /**
     * Parent id({@link Menu#id})
     */
    private String pid;

    private String levelCode;

    private String remark;

    private boolean disabled;

}
