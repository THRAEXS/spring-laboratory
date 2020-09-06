package org.thraex.business.hbis.vo;

import lombok.Data;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.platform.entity.FileDescriptor;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2020/09/03 18:02
 */
@Data
public class AdvertVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * {@link Advert#getId()}
     */
    private String id;

    /**
     * {@link FileDescriptor#getId()}
     */
    private String fid;

    /**
     * {@link FileDescriptor#getName()}
     */
    private String name;

    /**
     * {@link FileDescriptor#getPath()}
     */
    private String url;

    public AdvertVO() { }

    public AdvertVO(String id, String fid, String name, String url) {
        this.id = id;
        this.fid = fid;
        this.name = name;
        this.url = url;
    }

}
