package org.thraex.business.hbis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.business.hbis.vo.AdvertVO;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/03 17:15
 */
public interface AdvertService extends IService<Advert> {

    List<AdvertVO> listVO();

}
