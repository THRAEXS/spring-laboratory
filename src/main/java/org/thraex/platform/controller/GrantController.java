package org.thraex.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.entity.Role;
import org.thraex.platform.entity.RoleMenu;
import org.thraex.platform.entity.User;
import org.thraex.platform.entity.UserRole;
import org.thraex.platform.service.MenuService;
import org.thraex.platform.service.RoleMenuService;
import org.thraex.platform.service.RoleService;
import org.thraex.platform.service.UserRoleService;
import org.thraex.platform.service.UserService;
import org.thraex.platform.vo.GrantVO;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/31 09:49
 */
@RestController
@RequestMapping("api/grant")
public class GrantController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping
    public ResponseEntity<GrantVO> vo() {
        List<User> users = userService.list();
        List<Role> roles = roleService.list();
        List<Menu> menus = menuService.tree();

        return ResponseEntity.ok(new GrantVO().setUsers(users).setRoles(roles).setMenus(menus));
    }

    @GetMapping("role/{uid}")
    public ResponseEntity<List<UserRole>> role(@PathVariable String uid) {
        return ResponseEntity.ok(userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUid, uid)));
    }

    @PutMapping("role")
    public ResponseEntity<UserRole> role(@RequestBody List<UserRole> roles) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("menu/{rid}")
    public ResponseEntity<List<RoleMenu>> menu(@PathVariable String rid) {
        return ResponseEntity.ok(roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRid, rid)));
    }

    @PutMapping("menu")
    public ResponseEntity<RoleMenu> menu(@RequestBody List<RoleMenu> menus) {
        return ResponseEntity.ok(null);
    }

}
