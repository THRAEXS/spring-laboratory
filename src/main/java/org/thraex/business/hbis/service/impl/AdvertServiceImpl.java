package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.business.hbis.mapper.AdvertMapper;
import org.thraex.business.hbis.service.AdvertService;

/**
 * @author 鬼王
 * @date 2020/09/03 17:16
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements AdvertService {
}
