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
import org.thraex.util.Joiner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/09/06 15:07
 */
@Controller
@RequestMapping("hbis")
public class PortalController {

    private final String DELIMITER = "_";

    private static final String VIEW_NAVIGATOR = "hbis/navigator";

    @Value("${hbis.nav-code}")
    private String navCode;

    private SiteProperties sitProperties;

    private MenuService menuService;
    private AdvertService advertService;
    private NewsService newsService;
    private CaseService caseService;
    private AdditionalService additionalService;

    private static Company company;
    private static Additional additional;

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
        this.caseService = caseService;
        this.additionalService = additionalService;

        company = companyService.oneOrDefault();
        additional = additionalService.one();
    }

    private PortalVO vo() {
        /**
         * Compatible access: / or /hbis
         */
        SiteProperties.Portal site = sitProperties.getPortal();
        Optional.of(company).map(Company::getName).ifPresent(n -> site.setTitle(n));

        List<Menu> menus = menuService.tree(navCode).stream().findFirst().map(Menu::getChildren).orElse(Collections.emptyList());

        return new PortalVO(site, menus, company, advertService.listVO());
    }

    private void toModel(Model model, Consumer<PortalVO> set) {
        PortalVO vo = vo();
        set.accept(vo);
        model.addAttribute(vo);
    }

    @GetMapping
    public String index(Model model) {
        toModel(model, v -> v.setNews(newsService.list(5)).setCases(caseService.list(6)));
        return "hbis/index";
    }

    private PortalVO navigator(PortalVO vo, String key, String identifier, Function<String, String> content) {
        String root = Joiner.join(DELIMITER, navCode, key);

        String suffix = Optional.ofNullable(identifier).map(String::toUpperCase)
                .map(i -> Joiner.join(DELIMITER, key, i)).orElse(key);
        String active = Joiner.join(DELIMITER, Stream.of(navCode, suffix));

        vo.getNavigator().setNavs(menuService.list(root)).setActive(active).setContent(content.apply(suffix));

        return vo;
    }

    private enum FetchCompany {

        COMPANY(c -> c.getIntroduction()),
        COMPANY_CP(c -> c.getSituation()),
        COMPANY_ORG(c -> c.getOrganization()),
        COMPANY_SCOPE(c -> c.getScope()),
        COMPANY_PERSONNEL(c -> c.getPersonnel()),
        COMPANY_DC(c -> c.getHistory());

        private final Function<Company, String> get;

        FetchCompany(Function<Company, String> get) {
            this.get = get;
        }

        public String value() {
            return get.apply(company);
        }

    }

    private enum FetchAdditional {

        PROFESSIONAL(a -> a.getProfessional()),
        PROFESSIONAL_PIT(a -> a.getPit()),
        PROFESSIONAL_PAT(a -> a.getPat()),
        PROFESSIONAL_PCT(a -> a.getPct()),
        PROFESSIONAL_PNT(a -> a.getPnt()),
        PROFESSIONAL_PEG(a -> a.getPeg()),
        CASES(a -> a.getCases()),
        CASES_CIS(a -> a.getCis()),
        CASES_CAS(a -> a.getCas()),
        CASES_CCS(a -> a.getCcs()),
        CASES_CMS(a -> a.getCms()),
        CASES_CEG(a -> a.getCeg()),
        CULTURE(a -> a.getCulture()),
        CULTURE_CEV(a -> a.getCev()),
        CULTURE_CRA(a -> a.getCra()),
        CULTURE_CTB(a -> a.getCtb());

        private final Function<Additional, String> get;

        FetchAdditional(Function<Additional, String> get) {
            this.get = get;
        }

        public String value() {
            return get.apply(additional);
        }

    }

    @GetMapping({ "company", "company/{identifier}" })
    public String company(@PathVariable(required = false) String identifier, Model model) {
        final String key = "COMPANY";
        toModel(model, v -> navigator(v, key, identifier, c -> FetchCompany.valueOf(c).value()));
        return VIEW_NAVIGATOR;
    }

    @GetMapping({ "professional", "professional/{identifier}" })
    public String professional(@PathVariable(required = false) String identifier, Model model) {
        final String key = "PROFESSIONAL";
        toModel(model, v -> navigator(v, key, identifier, c -> FetchAdditional.valueOf(c).value()));
        return VIEW_NAVIGATOR;
    }

    @GetMapping({ "cases", "cases/{identifier}" })
    public String cases(@PathVariable(required = false) String identifier, Model model) {
        final String key = "CASES";
        toModel(model, v -> navigator(v, key, identifier, c -> FetchAdditional.valueOf(c).value()));
        return VIEW_NAVIGATOR;
    }

    @GetMapping({ "news", "news/{identifier}" })
    public String news(@PathVariable(required = false) String identifier, Model model) {
        final String key = "NEWS";
        toModel(model, v -> navigator(v, key, identifier, c -> FetchAdditional.valueOf(c).value()));
        return VIEW_NAVIGATOR;
    }

    @GetMapping({ "culture", "culture/{identifier}" })
    public String culture(@PathVariable(required = false) String identifier, Model model) {
        final String key = "CULTURE";
        toModel(model, v -> navigator(v, key, identifier, c -> FetchAdditional.valueOf(c).value()));
        return VIEW_NAVIGATOR;
    }

}
