package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.platform.entity.UserRole;
import org.thraex.platform.mapper.UserRoleMapper;
import org.thraex.platform.service.UserRoleService;

/**
 * @author 鬼王
 * @date 2020/08/31 10:52
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
