package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 鬼王
 * @date 2020/09/06 15:07
 */
@Controller
@RequestMapping("hbis")
public class HomeController {

    @Value("${hbis.url}")
    private String url;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("HBIS_URL", url);
        return "hbis/index";
    }

}
