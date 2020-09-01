package org.thraex.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thraex.base.properties.SiteProperties;
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
public class IndexController {

    @Autowired
    private SiteProperties properties;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("site", properties);

        User user = SecurityHolder.principal();
        model.addAttribute("user", user);

        List<String> roleIds = userRoleService.list(
                Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUid, user.getId()))
                .stream().map(UserRole::getRid).collect(Collectors.toList());
        List<String> menuIds = Optional.of(roleIds)
                .filter(it -> !it.isEmpty())
                .map(it -> roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().in(RoleMenu::getRid, roleIds))
                        .stream().map(RoleMenu::getMid).collect(Collectors.toList()))
                .orElse(null);
        model.addAttribute("menus", menuService.tree(menuIds));

        return "index";
    }

}
