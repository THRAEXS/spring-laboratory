package org.thraex.business.hbis.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.thraex.platform.entity.FileDescriptor;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2020/09/03 16:07
 */
@Data
@Accessors(chain = true)
public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * {@link FileDescriptor#getId()}
     */
    private String fid;

    public Advert() { }

    public Advert(String fid) {
        this.fid = fid;
    }

}
