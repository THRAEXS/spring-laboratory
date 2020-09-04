package org.thraex.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.service.MenuService;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/19 10:23
 */
@RestController
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> list() {
        return ResponseEntity.ok(menuService.list());
    }

    @GetMapping("tree")
    public ResponseEntity<List<Menu>> tree() {
        return ResponseEntity.ok(menuService.tree());
    }

    @GetMapping("{id}")
    public ResponseEntity<Menu> one(@PathVariable String id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @GetMapping("children/{identifier}")
    public ResponseEntity<List<Menu>> children(@PathVariable String identifier) {
        Menu parent = menuService.getOne(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getId, identifier).or().eq(Menu::getCode, identifier));

        return ResponseEntity.ok(menuService.list(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getPid, parent.getId()).orderByAsc(Menu::getLevelCode)));
    }

    @GetMapping("unique")
    public ResponseEntity<Boolean> uniqueness(String id, String code) {
        return ResponseEntity.ok(menuService.unique(id, code));
    }

    @PostMapping
    public ResponseEntity<Menu> save(@RequestBody Menu menu) {
        if (Strings.isBlank(menu.getPid())) { menu.setPid(null); }

        if (Strings.isBlank(menu.getId())) {
            menu.setLevelCode(menuService.nextLevel(menu.getPid()));
        }

        menuService.saveOrUpdate(menu.snapshot());
        return ResponseEntity.ok(menu);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Menu menu) {
        if (Strings.isBlank(menu.getPid())) { menu.setPid(null); }

        return ResponseEntity.ok(menuService.updateById(menu.snapshot()));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String levelCode) {
        return ResponseEntity.ok(menuService.remove(Wrappers.<Menu>lambdaQuery().likeRight(Menu::getLevelCode, levelCode)));
    }

}
