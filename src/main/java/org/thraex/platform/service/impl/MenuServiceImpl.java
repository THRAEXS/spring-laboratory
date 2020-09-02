package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.mapper.MenuMapper;
import org.thraex.platform.service.MenuService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/08/19 16:41
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> mock() {
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
                new Menu("id-0-0", "菜单管理", "MENU_MENU", "/menu", "id-0"),
                new Menu("id-0-1", "用户管理", "MENU_USER", "/user", "id-0"),
                new Menu("id-0-2", "角色管理", "MENU_ROLE", "/role", "id-0"),
                new Menu("id-0-2", "授权管理", "MENU_GRANT", "/grant", "id-0")
        ).collect(Collectors.toList());
    }

    @Override
    public List<Menu> tree() {
        return toTree(this.list(Wrappers.<Menu>lambdaQuery().orderByAsc(Menu::getLevelCode)));
    }

    @Override
    public List<Menu> tree(List<String> ids) {
        return Optional.ofNullable(ids)
                .filter(it -> !it.isEmpty())
                .map(it -> this.listByIds(it))
                .map(it -> toTree(it))
                .orElse(Collections.emptyList());
    }

    private List<Menu> toTree(List<Menu> list) {
        // TODO: Optimize
        List<Menu> roots = list.parallelStream()
                .filter(it -> Strings.isBlank(it.getPid()))
                .sorted((m1, m2) -> m2.getLevelCode().compareTo(m1.getLevelCode()))
                .collect(Collectors.toList());
        roots.forEach(it -> it.setChildren(list.parallelStream()
                .filter(s -> it.getId().equals(s.getPid()))
                .sorted(Comparator.comparing(Menu::getLevelCode))
                .collect(Collectors.toList())));

        return roots;
    }

    @Override
    public String nextLevel(String pid) {
        Menu parent = Strings.isBlank(pid) ? null : this.getById(pid);

        boolean pn = Objects.isNull(parent);
        List<Menu> list = this.list(Wrappers.<Menu>lambdaQuery()
                .isNull(pn, Menu::getPid).eq(!pn, Menu::getPid, pid)
                .orderByDesc(Menu::getLevelCode));

        final String empty = "";
        final String format = "%08d";
        String pl = Optional.ofNullable(parent).map(Menu::getLevelCode).orElse(empty);
        String max = list.stream().findFirst()
                .map(it -> it.getLevelCode().substring(it.getLevelCode().length() - 8))
                .orElse(String.format(format, 0));
        String next = String.format(format, Integer.valueOf(max) + 1);

        return String.format("%s%s", pl, next);
    }

    @Override
    public boolean unique(String id, String code) {
        int count = this.count(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getCode, code).ne(Strings.isNotBlank(id), Menu::getId, id));
        return count > 0 ? false : true;
    }

}
