package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.business.hbis.entity.Case;
import org.thraex.business.hbis.mapper.CaseMapper;
import org.thraex.business.hbis.service.CaseService;

/**
 * @author 鬼王
 * @date 2020/09/04 16:16
 */
@Service
public class CaseServiceImpl extends ServiceImpl<CaseMapper, Case> implements CaseService {
}
