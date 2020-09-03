package org.thraex.business.hbis.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.thraex.business.hbis.entity.Advert;

/**
 * @author 鬼王
 * @date 2020/09/03 18:02
 */
@Data
@Accessors(chain = true)
public class AdvertVO extends Advert {

    private String name;

    private String path;

    public AdvertVO() { }

    public AdvertVO(String id, String fid) {
        this.setId(id);
        this.setFid(fid);
    }

    public AdvertVO(String id, String fid, String name, String path) {
        this(id, fid);
        this.name = name;
        this.path = path;
    }

}
