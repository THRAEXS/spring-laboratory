package org.agriculture.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.agriculture.platform.entity.User;

/**
 * @author MLeo
 * @date 2020/08/27 09:58
 */
public interface UserService extends IService<User> {

    boolean unique(String id, String username);

    User findByUsername(String username);

}
