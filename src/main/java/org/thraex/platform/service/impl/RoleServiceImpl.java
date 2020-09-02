package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
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

    @Override
    public boolean unique(String id, String code) {
        int count = this.count(Wrappers.<Role>lambdaQuery()
                .eq(Role::getCode, code).ne(Strings.isNotBlank(id), Role::getId, id));
        return count > 0 ? false : true;
    }

}
