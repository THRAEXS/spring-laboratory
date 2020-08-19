package org.thraex.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thraex.base.properties.SiteProperties;

/**
 * @author 鬼王
 * @date 2020/08/19 10:03
 */
@Controller
public class IndexController {

    @Autowired
    private SiteProperties siteProperties;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("site", siteProperties);
        return "index";
    }

}
