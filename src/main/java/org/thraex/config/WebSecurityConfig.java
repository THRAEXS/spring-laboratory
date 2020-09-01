package org.thraex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thraex.base.properties.SiteProperties;

/**
 * @author 鬼王
 * @date 2020/08/11 11:46
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SiteProperties properties;

    /**
     * Reference
     * InitializeUserDetailsBeanManagerConfigurer.InitializeUserDetailsManagerConfigurer#configure(AuthenticationManagerBuilder)
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and().authorizeRequests()
                    .antMatchers(properties.permit()).permitAll()
                    .anyRequest().authenticated()
                .and().formLogin()
                    .defaultSuccessUrl(properties.successUrl())
                .and().httpBasic();
    }

}
