package org.hbis.platform.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2020/08/31 10:46
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String uid;

    private String rid;

}
