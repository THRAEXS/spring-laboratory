package org.thraex.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.thraex.platform.entity.Role;

/**
 * @author 鬼王
 * @date 2020/08/27 09:58
 */
public interface RoleService extends IService<Role> {

    boolean unique(String id, String code);

}
