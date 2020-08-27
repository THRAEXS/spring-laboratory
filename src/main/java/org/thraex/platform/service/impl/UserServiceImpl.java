package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.thraex.platform.entity.User;
import org.thraex.platform.mapper.UserMapper;
import org.thraex.platform.service.UserService;

/**
 * @author 鬼王
 * @date 2020/08/27 09:59
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private SecurityProperties properties;

    @Override
    public boolean unique(String id, String username) {
        String low = username.toLowerCase();

        SecurityProperties.User admin = properties.getUser();
        if (admin.getName().equalsIgnoreCase(username)) { return false; }

        int count = this.count(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, low).ne(Strings.isNotBlank(id), User::getId, id));
        return count > 0 ? false : true;
    }

}
