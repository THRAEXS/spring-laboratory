package org.agriculture.platform.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author MLeo
 * @date 2020/08/31 10:47
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String rid;

    private String mid;

}
