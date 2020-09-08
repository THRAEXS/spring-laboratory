package org.hbis.business.hbis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hbis.base.entity.Entity;
import org.hbis.platform.entity.FileDescriptor;

/**
 * @author 鬼王
 * @date 2020/09/04 15:49
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Case extends Entity<Case> {

    private String name;

    /**
     * {@link FileDescriptor#getId()}
     */
    private String fid;

    /**
     * {@link FileDescriptor#getPath()} ()}
     */
    private String cover;

}
