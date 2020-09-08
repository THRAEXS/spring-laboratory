package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thraex.base.properties.SiteProperties;
import org.thraex.business.hbis.entity.Additional;
import org.thraex.business.hbis.entity.Company;
import org.thraex.business.hbis.service.AdditionalService;
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

    @Value("${hbis.nav-code}")
    private String navCode;

    private SiteProperties sitProperties;

    private MenuService menuService;
    private AdvertService advertService;
    private NewsService newsService;
    private CompanyService companyService;
    private CaseService caseService;
    private AdditionalService additionalService;

    private Company company;
    private Additional additional;

    public PortalController(SiteProperties sitProperties,
                            MenuService menuService,
                            AdvertService advertService,
                            NewsService newsService,
                            CompanyService companyService,
                            CaseService caseService,
                            AdditionalService additionalService) {
        this.sitProperties = sitProperties;
        this.menuService = menuService;
        this.advertService = advertService;
        this.newsService = newsService;
        this.companyService = companyService;
        this.caseService = caseService;
        this.additionalService = additionalService;

        this.company = companyService.oneOrDefault();
        this.additional = additionalService.one();
    }

    private PortalVO base() {
        /**
         * Compatible access: / or /hbis
         */
        SiteProperties.Portal site = sitProperties.getPortal();
        Optional.of(company).map(Company::getName).ifPresent(n -> site.setTitle(n));

        List<Menu> menus = menuService.tree(navCode).stream().findFirst().map(Menu::getChildren).orElse(Collections.emptyList());

        return new PortalVO(site, menus, company, advertService.listVO());
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute(base().setNews(newsService.list(5)).setCases(caseService.list(6)));
        return "hbis/index";
    }

    @GetMapping({ "company", "company/{identifier}"})
    public String company(@PathVariable(required = false) String identifier, Model model) {
        model.addAttribute(base().setIdentifier(identifier));
        return "hbis/company";
    }

    @GetMapping({ "professional", "professional/{identifier}"})
    public String professional(@PathVariable(required = false) String identifier, Model model) {
        model.addAttribute(base().setIdentifier(identifier));
        return "hbis/professional";
    }

    @GetMapping({ "cases", "cases/{identifier}"})
    public String cases(@PathVariable(required = false) String identifier, Model model) {
        model.addAttribute(base().setIdentifier(identifier));
        return "hbis/cases";
    }

    @GetMapping({ "news", "news/{identifier}"})
    public String news(@PathVariable(required = false) String identifier, Model model) {
        model.addAttribute(base().setIdentifier(identifier));
        return "hbis/news";
    }

    @GetMapping({ "culture", "culture/{identifier}"})
    public String culture(@PathVariable(required = false) String identifier, Model model) {
        model.addAttribute(base().setIdentifier(identifier));
        return "hbis/culture";
    }

}
