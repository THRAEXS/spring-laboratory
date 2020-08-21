package org.thraex.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.service.MenuService;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/19 10:23
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("tree")
    public ResponseEntity<List<Menu>> tree() {
        return ResponseEntity.ok(menuService.tree());
    }

}
