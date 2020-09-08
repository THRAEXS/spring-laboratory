package org.hbis.business.hbis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.hbis.business.hbis.entity.Case;
import org.hbis.business.hbis.mapper.CaseMapper;
import org.hbis.business.hbis.service.CaseService;
import org.hbis.util.Joiner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2020/09/04 16:16
 */
@Service
public class CaseServiceImpl extends ServiceImpl<CaseMapper, Case> implements CaseService {

    @Value("${thraex.file.access-prefix}")
    private String accessPrefix;

    @Override
    public List<Case> list(long size) {
        return this.list(Wrappers.<Case>lambdaQuery().orderByAsc(Case::getCreateTime).last(size > 0, "LIMIT " + size))
                .stream().map(it -> it.setCover(Joiner.path(accessPrefix, it.getCover()))).collect(Collectors.toList());
    }

}
