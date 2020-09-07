package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thraex.base.properties.SiteProperties;
import org.thraex.business.hbis.entity.Company;
import org.thraex.business.hbis.service.AdvertService;
import org.thraex.business.hbis.service.CaseService;
import org.thraex.business.hbis.service.CompanyService;
import org.thraex.business.hbis.service.NewsService;
import org.thraex.business.hbis.vo.PortalVO;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.service.MenuService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/09/06 15:07
 */
@Controller
@RequestMapping("hbis")
public class PortalController {

    @Autowired
    private SiteProperties sitProperties;

    @Value("${hbis.nav-code}")
    private String navCode;

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CaseService caseService;

    private PortalVO base() {
        /**
         * Compatible access: / or /hbis
         */
        SiteProperties.Portal site = sitProperties.getPortal();
        Optional.ofNullable(companyService.one()).map(Company::getName).ifPresent(n -> site.setTitle(n));

        List<Menu> menus = menuService.tree(navCode).stream().findFirst().map(Menu::getChildren).orElse(Collections.emptyList());

        return new PortalVO(site, menus, advertService.listVO());
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(base()
                .setCompany(companyService.one())
                .setNews(newsService.list(5))
                .setCases(caseService.list(6)));
        return "hbis/index";
    }

    @GetMapping("company")
    public String company() {
        return null;
    }

}
