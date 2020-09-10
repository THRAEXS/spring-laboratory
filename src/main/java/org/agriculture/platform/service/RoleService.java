package org.agriculture.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.agriculture.platform.entity.Role;

/**
 * @author MLeo
 * @date 2020/08/27 09:58
 */
public interface RoleService extends IService<Role> {

    boolean unique(String id, String code);

}
