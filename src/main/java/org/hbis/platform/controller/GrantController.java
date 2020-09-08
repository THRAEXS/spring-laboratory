package org.hbis.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hbis.platform.entity.Menu;
import org.hbis.platform.entity.Role;
import org.hbis.platform.entity.RoleMenu;
import org.hbis.platform.entity.User;
import org.hbis.platform.entity.UserRole;
import org.hbis.platform.service.MenuService;
import org.hbis.platform.service.RoleMenuService;
import org.hbis.platform.service.RoleService;
import org.hbis.platform.service.UserRoleService;
import org.hbis.platform.service.UserService;
import org.hbis.platform.vo.GrantVO;

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

    @PutMapping("role/{uid}")
    public ResponseEntity<List<UserRole>> role(@PathVariable String uid, @RequestBody List<UserRole> roles) {
        userRoleService.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUid, uid));
        userRoleService.saveBatch(roles);
        return ResponseEntity.ok(roles);
    }

    @GetMapping("menu/{rid}")
    public ResponseEntity<List<RoleMenu>> menu(@PathVariable String rid) {
        return ResponseEntity.ok(roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRid, rid)));
    }

    @PutMapping("menu/{rid}")
    public ResponseEntity<List<RoleMenu>> menu(@PathVariable String rid, @RequestBody List<RoleMenu> menus) {
        roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRid, rid));
        roleMenuService.saveBatch(menus);
        return ResponseEntity.ok(menus);
    }

}
