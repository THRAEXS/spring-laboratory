package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.platform.entity.Role;
import org.thraex.platform.mapper.RoleMapper;
import org.thraex.platform.service.RoleService;

/**
 * @author 鬼王
 * @date 2020/08/28 21:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
