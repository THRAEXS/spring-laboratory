package org.agriculture.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.agriculture.platform.entity.RoleMenu;
import org.agriculture.platform.mapper.RoleMenuMapper;
import org.agriculture.platform.service.RoleMenuService;

/**
 * @author MLeo
 * @date 2020/08/31 10:53
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
