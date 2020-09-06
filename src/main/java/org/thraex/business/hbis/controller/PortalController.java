package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thraex.base.properties.SiteProperties;
import org.thraex.business.hbis.service.AdvertService;
import org.thraex.business.hbis.vo.PortalVO;

/**
 * @author 鬼王
 * @date 2020/09/06 15:07
 */
@Controller
@RequestMapping("hbis")
public class PortalController {

    @Autowired
    private SiteProperties sitProperties;

    @Value("${hbis.url}")
    private String url;

    @Autowired
    private AdvertService advertService;

    @GetMapping
    public String index(Model model) {
        /**
         * Compatible access: / or /hbis
         */
        SiteProperties.Portal site = sitProperties.getPortal();
        model.addAttribute("portal", new PortalVO(site, url, advertService.listVO()));

        return "hbis/index";
    }

    @GetMapping("company")
    public String company() {
        return null;
    }

}
