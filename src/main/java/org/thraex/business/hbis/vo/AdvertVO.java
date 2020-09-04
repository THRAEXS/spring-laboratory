package org.thraex.business.hbis.vo;

import lombok.Data;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.platform.entity.FileDescriptor;

/**
 * @author 鬼王
 * @date 2020/09/03 18:02
 */
@Data
public class AdvertVO extends Advert {

    /**
     * {@link FileDescriptor#getName()}
     */
    private String name;

    /**
     * {@link FileDescriptor#getPath()}
     */
    private String url;

    public AdvertVO() { }

    public AdvertVO(String id, String fid) {
        this.setId(id);
        this.setFid(fid);
    }

    public AdvertVO(String id, String fid, String name, String url) {
        this(id, fid);
        this.name = name;
        this.url = url;
    }

}
