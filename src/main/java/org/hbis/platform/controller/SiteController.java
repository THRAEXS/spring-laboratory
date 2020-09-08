package org.hbis.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.hbis.base.properties.SiteProperties;
import org.hbis.platform.entity.Menu;
import org.hbis.platform.entity.RoleMenu;
import org.hbis.platform.entity.User;
import org.hbis.platform.entity.UserRole;
import org.hbis.platform.service.MenuService;
import org.hbis.platform.service.RoleMenuService;
import org.hbis.platform.service.UserRoleService;
import org.hbis.security.SecurityHolder;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2020/08/19 10:03
 */
@Controller
public class SiteController {

    @Autowired
    private SiteProperties sitProperties;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String index(Model model) {
        Consumer<Model> c = sitProperties.isEnabled() ?
                m -> m.addAttribute("site", sitProperties.getPortal()) : m -> admin(m);
        c.accept(model);

        return sitProperties.index();
    }

    @GetMapping("admin")
    public String admin(Model model) {
        SiteProperties.Admin admin = sitProperties.getAdmin();
        model.addAttribute("site", admin);

        // TODO: restart bug, User authorities and Authentication authorities
        User user = SecurityHolder.principal();
        model.addAttribute("user", user);

        model.addAttribute("menus", Optional.of(user.getUsername())
                .filter(u -> u.equals(securityProperties.getUser().getName()))
                .map(u -> menuService.tree()).orElse(menus(user.getId())));

        return admin.view();
    }

    private List<Menu> menus(String uid) {
        List<String> roleIds = userRoleService.list(
                Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUid, uid))
                .stream().map(UserRole::getRid).collect(Collectors.toList());
        List<String> menuIds = Optional.of(roleIds)
                .filter(it -> !it.isEmpty())
                .map(it -> roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getRid, roleIds))
                        .stream().map(RoleMenu::getMid).collect(Collectors.toList()))
                .orElse(null);

        return menuService.tree(menuIds);
    }

}
