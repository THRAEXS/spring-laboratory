package org.hbis.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.hbis.platform.entity.UserRole;
import org.hbis.platform.mapper.UserRoleMapper;
import org.hbis.platform.service.UserRoleService;

/**
 * @author 鬼王
 * @date 2020/08/31 10:52
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
