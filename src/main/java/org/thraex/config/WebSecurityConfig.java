package org.thraex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.thraex.base.properties.SiteProperties;
import org.thraex.security.filter.CryptoAuthenticationFilter;

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

    @Bean
    public CryptoAuthenticationFilter authenticationFilter() throws Exception {
        CryptoAuthenticationFilter filter = new CryptoAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        //filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        return filter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and().authorizeRequests()
                    .antMatchers(properties.permits()).permitAll()
                    .anyRequest().authenticated()
                .and().formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl(properties.redirect())
                .and()
                    .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
