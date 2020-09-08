package org.hbis.business.hbis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hbis.base.entity.Entity;
import org.hbis.platform.entity.FileDescriptor;

/**
 * @author 鬼王
 * @date 2020/09/06 21:28
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Company extends Entity<Company> {

    private String name;

    private String address;

    private String zipCode;

    private String phone;

    private String fax;

    private String contact;

    private String mobile;

    /**
     * {@link FileDescriptor#getId()}
     */
    private String coverId;

    /**
     * {@link FileDescriptor#getPath()}
     */
    private String coverPath;

    /**
     * {@link FileDescriptor#getId()}
     */
    private String mapId;

    /**
     * {@link FileDescriptor#getPath()}
     */
    private String mapPath;

    private String introduction;

    private String situation;

    private String organization;

    private String scope;

    private String personnel;

    private String history;

}
