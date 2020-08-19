package org.thraex.platform.service.impl;

import org.springframework.stereotype.Service;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.service.MenuService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/08/19 16:41
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Override
    public List<Menu> list() {
        return Stream.of(
                new Menu().setId("id-X-0").setName("Business-01"),
                new Menu().setId("id-XX").setName("Business"),
                new Menu().setId("id-XX-0").setName("Business-XX-0").setPid("id-XX"),
                new Menu().setId("id-XX-1").setName("Business-XX-1").setPid("id-XX"),
                new Menu().setId("id-XX-2").setName("Business-XX-2").setPid("id-XX"),
                new Menu().setId("id-XX-3").setName("Business-XX-3").setPid("id-XX"),
                new Menu().setId("id-X-1").setName("Business-02"),
                new Menu().setId("id-X-3").setName("Business-03"),
                new Menu("id-0", "系统设置", "MENU_SETTING", null, null),
                new Menu("id-0-0", "菜单维护", "MENU_MENU", "/menu", "id-0"),
                new Menu("id-0-1", "角色维护", "MENU_ROLE", "/role", "id-0"),
                new Menu("id-0-2", "用户维护", "MENU_USER", "/user", "id-0")).collect(Collectors.toList());
    }

    @Override
    public List<Menu> tree() {
        List<Menu> list = this.list();
        List<Menu> root = list.parallelStream().filter(it -> Objects.isNull(it.getPid())).collect(Collectors.toList());
        root.forEach(it -> it.setChildren(list.parallelStream()
                .filter(s -> it.getId().equals(s.getPid())).collect(Collectors.toList())));
        return root;
    }

}
