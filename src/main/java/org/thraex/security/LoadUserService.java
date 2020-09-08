package org.thraex.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import org.thraex.platform.entity.Role;
import org.thraex.platform.entity.User;
import org.thraex.platform.entity.UserRole;
import org.thraex.platform.service.RoleService;
import org.thraex.platform.service.UserRoleService;
import org.thraex.platform.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityProperties.User user = properties.getUser();
        return Optional.of(username)
                .filter(u -> u.equals(user.getName()))
                .map(u -> User.withId(u).nickname(u.toUpperCase()).username(u)
                        .password(user.getPassword()).passwordEncoder(p -> passwordEncoder.encode(p))
                        .roles(StringUtils.toStringArray(user.getRoles())).build())
                .orElseGet(() -> Optional.ofNullable(userService.findByUsername(username))
                        .map(it -> {
                            List<String> roleIds = userRoleService.list(Wrappers.<UserRole>lambdaQuery()
                                    .eq(UserRole::getUid, it.getId())).parallelStream().map(UserRole::getRid)
                                    .collect(Collectors.toList());
                            List<String> roleCodes = roleService.listByIds(roleIds)
                                    .parallelStream().map(Role::getCode).collect(Collectors.toList());
                            return it.setAuthorities(roleCodes);
                        }).orElseThrow(() -> new UsernameNotFoundException("The user does exist")));
    }

}