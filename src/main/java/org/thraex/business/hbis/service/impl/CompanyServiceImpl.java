package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thraex.business.hbis.entity.Company;
import org.thraex.business.hbis.mapper.CompanyMapper;
import org.thraex.business.hbis.service.CompanyService;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/09/07 09:01
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private DefaultCompany dc;

    @Override
    public Company one() {
        return this.list().stream().findFirst().orElse(null);
    }

    @Override
    public Company oneOrDefault() {
        return Optional.ofNullable(one()).orElse(dc);
    }

    @Data
    @Component
    @ConfigurationProperties("hbis")
    private static class DefaultCompany extends Company { }

}
