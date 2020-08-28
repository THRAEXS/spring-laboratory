package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.mapper.MenuMapper;
import org.thraex.platform.service.MenuService;

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
                new Menu("id-0-2", "授权管理", "MENU_ROLE", "/grant", "id-0")
        ).collect(Collectors.toList());
    }

    @Override
    public List<Menu> tree() {
        List<Menu> list = this.list();
        List<Menu> root = list.parallelStream().filter(it -> Strings.isBlank(it.getPid())).collect(Collectors.toList());
        root.forEach(it -> it.setChildren(list.parallelStream()
                .filter(s -> it.getId().equals(s.getPid())).collect(Collectors.toList())));
        return root;
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
                .map(it -> it.getLevelCode().replace(pl, empty))
                .orElse(String.format(format, 0));
        String next = String.format(format, Integer.valueOf(max) + 1);

        return String.format("%s%s", pl, next);
    }

    public static void main(String[] args) {
        //list.stream().findFirst().orElse("");

        //String parentLevel = null;
        //final int step = 8;
        //int len = Optional.ofNullable(parentLevel).map(l -> l.trim().length()).orElse(0);
        //Preconditions.checkArgument(len % step != 0, "Level code length is not correct");
        //
        //list.parallelStream()
        //        .map(Menu::getLevelCode)
        //        .filter(it -> Strings.isNotBlank(it) && it.length() == len + step)
        //        .sorted(Comparator.reverseOrder()).findFirst();

        //System.out.println(-0%8);
        //System.out.println(0%8);
        //System.out.println(8%8);
        //System.out.println(16%8);
        //System.out.println(24%8);
        //System.out.println(32%8);
        //System.out.println(7%8);
        //System.out.println(15%8);
        //System.out.println(20%8);
        //List<String> list = Arrays.asList(
        //        //"00000001",
        //        //"00000000",
        //        //"00000100",
        //        //"00000003",
        //        //"00000010",
        //        //"00000002",
        //        //"00000004"
        //);
        //String a = list.stream().sorted(Comparator.reverseOrder()).findFirst().orElse("00000000");
        //System.out.println(a);

        //System.out.println(String.format("%08d", 0));
        //System.out.println(String.format("%08d", 1));
        //System.out.println(String.format("%08d", 12));
        String a = "00000089";
        String b = "0000000100000089";
        String c = "000000010000008900000004";
        System.out.println(a.substring(a.length() - 8));
        System.out.println(a.substring(0, a.length() - 8));
        System.out.println(b.substring(b.length() - 8));
        System.out.println(b.substring(0, b.length() - 8));
        System.out.println(c.substring(c.length() - 8));
        System.out.println(c.substring(0, c.length() - 8));
    }

}
