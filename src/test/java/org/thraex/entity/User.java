package org.thraex.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/08/11 09:59
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Entity<User> {

    private String name;

    private int sex;

}
