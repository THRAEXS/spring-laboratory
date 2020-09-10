package org.agriculture.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.agriculture.base.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MLeo
 * @date 2020/08/19 10:21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends Entity<Menu> {

    private static final long serialVersionUID = 1L;

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

    private transient List<Menu> children = new ArrayList<>();

    public Menu() { }

    public Menu(String id, String name, String code, String url, String pid) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.url = url;
        this.pid = pid;
    }

}
