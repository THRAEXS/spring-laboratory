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
import org.thraex.business.hbis.vo.PortalVO;
import org.thraex.platform.entity.Menu;
import org.thraex.platform.service.MenuService;

import java.util.Collections;
import java.util.List;

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

    private PortalVO base() {
        /**
         * Compatible access: / or /hbis
         */
        SiteProperties.Portal site = sitProperties.getPortal();

        List<Menu> menus = menuService.tree(navCode).stream().findFirst().map(Menu::getChildren).orElse(Collections.emptyList());
        PortalVO vo = new PortalVO(site, menus, advertService.listVO());

        return vo;
    }

    @GetMapping
    public String index(Model model) {
        Company c = new Company();
        c.setCoverPath("/assets/images/hbis/company/cover.png");
        c.setIntroduction("邯郸市邯钢集团信达科技有限公司（简称信达科技），系河钢集团邯钢公司全资子公司，注册资本500万元，1997年成立。历经邯钢20年基础自动化的高速发展和信息化改造的曲折进程，通过引进消化吸收、联合、自主等途径，实施了涵盖钢铁冶金行业全工艺流程的自动化、信息化系统。凭借服务邯钢的经验和技术积累，信达科技面向社会持续发展，已经成长为能够提供自动化、信息化、通讯、监控等系统解决方案的高新技术企业。");
        model.addAttribute(base().setCompany(c));
        return "hbis/index";
    }

    @GetMapping("company")
    public String company() {
        return null;
    }

}
