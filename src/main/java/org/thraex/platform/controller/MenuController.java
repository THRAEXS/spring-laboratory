package org.thraex.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thraex.platform.entity.Menu;

/**
 * @author 鬼王
 * @date 2020/08/19 10:23
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    public String list() {
        return null;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setId("1").setCode("2");
        System.out.println(menu);
    }

}
