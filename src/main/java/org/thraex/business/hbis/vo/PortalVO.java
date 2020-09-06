package org.thraex.business.hbis.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.thraex.base.properties.SiteProperties;

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

    private String url;

    private List<AdvertVO> adverts;

    public PortalVO() { }

    public PortalVO(SiteProperties.Portal site, String url) {
        this.site = site;
        this.url = url;
    }

    public PortalVO(SiteProperties.Portal site, String url, List<AdvertVO> adverts) {
        this(site, url);
        this.adverts = adverts;
    }

}
