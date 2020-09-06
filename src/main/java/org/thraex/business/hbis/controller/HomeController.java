package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thraex.base.properties.SiteProperties;
import org.thraex.business.hbis.service.AdvertService;

/**
 * @author 鬼王
 * @date 2020/09/06 15:07
 */
@Controller
@RequestMapping("hbis")
public class HomeController {

    @Autowired
    private SiteProperties sitProperties;

    @Value("${hbis.url}")
    private String url;

    @Autowired
    private AdvertService advertService;

    @GetMapping
    public String home(Model model) {
        /**
         * Compatible access: / or /hbis
         */
        model.addAttribute("site", sitProperties.getPortal());
        model.addAttribute("HBIS_URL", url);

        model.addAttribute("adverts", advertService.listVO());

        return "hbis/index";
    }

}
