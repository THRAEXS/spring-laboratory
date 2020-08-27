package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.platform.mapper.UserMapper;
import org.thraex.platform.entity.User;
import org.thraex.platform.service.UserService;

/**
 * @author 鬼王
 * @date 2020/08/27 09:59
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
