package org.thraex.security;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thraex.platform.entity.User;
import org.thraex.platform.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * {@link UsernamePasswordAuthenticationFilter#attemptAuthentication(HttpServletRequest, HttpServletResponse)}
 * 
 * @author 鬼王
 * @date 2020/08/21 16:12
 */
@Service
public class LoadUserService implements UserDetailsService {

    /**
     * Reference {@link DaoAuthenticationProvider#DaoAuthenticationProvider()}
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Reference {@link UserDetailsServiceAutoConfiguration#inMemoryUserDetailsManager(SecurityProperties, ObjectProvider)}
     */
    @Autowired
    private SecurityProperties properties;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityProperties.User user = properties.getUser();
        return Optional.of(username)
                .filter(u -> u.equals(user.getName()))
                .map(u -> User.withId(u).nickname(u.toUpperCase()).username(u)
                        .password(user.getPassword()).passwordEncoder(p -> passwordEncoder.encode(p))
                        .roles(StringUtils.toStringArray(user.getRoles())).build())
                .orElseGet(() -> {
                    User dbUser = Optional.ofNullable(userService.findByUsername(username))
                            .orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));

                    if (!dbUser.isEnabled()) {
                        throw new UsernameNotFoundException("账号已禁用，请联系管理员");
                    }

                    return dbUser;
                });
    }

}
