package org.agriculture.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MLeo
 * @date 2020/08/27 15:25
 */
@Configuration
@MapperScan("org.agriculture.**.mapper")
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor innerInterceptor() {
        return new PaginationInterceptor();
    }

}
