package org.thraex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author 鬼王
 * @date 2020/08/10 17:40
 */
@SpringBootApplication
@ConfigurationPropertiesScan("org.thraex.base.properties")
@MapperScan("org.thraex.**.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
