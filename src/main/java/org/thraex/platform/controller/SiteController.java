package org.thraex.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thraex.base.properties.SiteProperties;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.entity.RoleMenu;
import org.thraex.platform.entity.User;
import org.thraex.platform.entity.UserRole;
import org.thraex.platform.service.MenuService;
import org.thraex.platform.service.RoleMenuService;
import org.thraex.platform.service.UserRoleService;
import org.thraex.security.SecurityHolder;

import java.util.List;
import java.util.Optional;
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
        SiteProperties.Mode mode = sitProperties.getMode();
        if (mode.isEnabled()) {
            admin(model);
        } else {
            model.addAttribute("view", sitProperties.getIndex().getView());
        }

        return mode.index();
    }

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("admin", sitProperties.getAdmin());

        // TODO: restart bug, User authorities and Authentication authorities
        User user = SecurityHolder.principal();
        model.addAttribute("user", user);

        model.addAttribute("menus", Optional.of(user.getUsername())
                .filter(u -> u.equals(securityProperties.getUser().getName()))
                .map(u -> menuService.tree()).orElse(menus(user.getId())));

        return "admin";
    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        model.addAttribute("view", sitProperties.getAdmin().getDashboard());
        return "dashboard";
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
