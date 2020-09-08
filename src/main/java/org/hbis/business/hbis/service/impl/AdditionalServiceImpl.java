package org.hbis.business.hbis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.hbis.business.hbis.entity.Additional;
import org.hbis.business.hbis.mapper.AdditionalMapper;
import org.hbis.business.hbis.service.AdditionalService;

/**
 * @author 鬼王
 * @date 2020/09/07 22:18
 */
@Service
public class AdditionalServiceImpl extends ServiceImpl<AdditionalMapper, Additional> implements AdditionalService {

    @Override
    public Additional one() {
        return this.getOne(Wrappers.lambdaQuery());
    }

}
