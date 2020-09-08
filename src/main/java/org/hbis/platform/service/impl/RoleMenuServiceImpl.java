package org.hbis.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.hbis.platform.entity.RoleMenu;
import org.hbis.platform.mapper.RoleMenuMapper;
import org.hbis.platform.service.RoleMenuService;

/**
 * @author 鬼王
 * @date 2020/08/31 10:53
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
