package org.thraex.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 鬼王
 * @date 2020/08/19 10:23
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @GetMapping
    public String index() {
        // TODO: menu tree
        return "menu/index";
    }

    @GetMapping("edit")
    public String edit() {
        return "menu/edit";
    }

}
