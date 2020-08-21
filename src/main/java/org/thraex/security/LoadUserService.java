package org.thraex.security;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 鬼王
 * @date 2020/08/21 16:12
 */
@Service
public class LoadUserService implements UserDetailsService {

    /**
     * Reference {@link DaoAuthenticationProvider#DaoAuthenticationProvider()}
     */
    //private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Reference {@link UserDetailsServiceAutoConfiguration#inMemoryUserDetailsManager(SecurityProperties, ObjectProvider)}
     */
    @Autowired
    private SecurityProperties properties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityProperties.User user = properties.getUser();
        UserDetails build = User.withUsername(user.getName())
                //.password("{bcrypt}" + user.getPassword())
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(StringUtils.toStringArray(user.getRoles())).build();
        //return new User(user.getName(), user.getPassword(), user.getRoles());
        return build;
    }

}
