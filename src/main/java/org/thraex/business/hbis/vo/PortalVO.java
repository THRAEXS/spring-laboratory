package org.thraex.business.hbis.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.thraex.base.properties.SiteProperties;
import org.thraex.business.hbis.entity.Case;
import org.thraex.business.hbis.entity.Company;
import org.thraex.business.hbis.entity.News;
import org.thraex.platform.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/06 17:47
 */
@Data
@Accessors(chain = true)
public class PortalVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private SiteProperties.Portal site;

    private List<Menu> navs;

    private List<AdvertVO> adverts;

    private Company company;

    private List<News> news;

    private List<Case> cases;

    private Navigator navigator;

    public PortalVO() {
        this.navigator = new Navigator();
    }

    public PortalVO(SiteProperties.Portal site, List<Menu> navs) {
        this();
        this.site = site;
        this.navs = navs;
    }

    public PortalVO(SiteProperties.Portal site, List<Menu> navs, List<AdvertVO> adverts) {
        this(site, navs);
        this.adverts = adverts;
    }

    public PortalVO(SiteProperties.Portal site, List<Menu> navs, Company company, List<AdvertVO> adverts) {
        this(site, navs, adverts);
        this.company = company;
    }

    @Data
    @Accessors(chain = true)
    public class Navigator {

        private List<Menu> navs;

        private String active;

        private String content;

    }

}
