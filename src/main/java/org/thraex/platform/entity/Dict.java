package org.thraex.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/09 19:00
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Dict extends Entity<Dict> {

    private String name;

    private String code;

    /**
     * Parent id({@link Dict#id})
     */
    private String pid;

    private String levelCode;

    private String remark;

    private transient List<Dict> children = new ArrayList<>();

}
