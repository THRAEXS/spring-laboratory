package org.hbis.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hbis.platform.entity.User;

/**
 * @author 鬼王
 * @date 2020/08/27 09:58
 */
public interface UserService extends IService<User> {

    boolean unique(String id, String username);

    User findByUsername(String username);

}
