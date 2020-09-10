package org.agriculture.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.agriculture.platform.entity.UserRole;
import org.agriculture.platform.mapper.UserRoleMapper;
import org.agriculture.platform.service.UserRoleService;

/**
 * @author MLeo
 * @date 2020/08/31 10:52
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
