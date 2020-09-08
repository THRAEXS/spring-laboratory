package org.hbis.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hbis.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/08/28 21:19
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Role extends Entity<Role> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String remark;

}
