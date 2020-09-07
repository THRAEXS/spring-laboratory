package org.thraex.business.hbis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.thraex.business.hbis.entity.Company;

/**
 * @author 鬼王
 * @date 2020/09/07 09:00
 */
public interface CompanyService extends IService<Company> {

    Company one();

    Company oneOrDefault();

}
