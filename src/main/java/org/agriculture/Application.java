package org.agriculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author MLeo
 * @date 2020/08/10 17:40
 */
@SpringBootApplication
@ConfigurationPropertiesScan("org.agriculture.base.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
