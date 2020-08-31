package org.thraex.platform.vo;

import org.thraex.platform.entity.Menu;
import org.thraex.platform.entity.Role;
import org.thraex.platform.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/31 09:52
 */
public class GrantVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Menu> menus;

    private List<Role> roles;

    private List<User> users;

    public List<Menu> getMenus() {
        return menus;
    }

    public GrantVO setMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public GrantVO setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public GrantVO setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "GrantVO{" +
                "menus=" + menus +
                ", roles=" + roles +
                ", users=" + users +
                '}';
    }
}
