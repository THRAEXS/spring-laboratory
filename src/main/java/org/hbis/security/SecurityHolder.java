package org.hbis.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.hbis.platform.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/08/27 11:41
 */
public abstract class SecurityHolder {

    public static Authentication authentication() {
        return Optional.of(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication).orElse(null);
    }

    public static String id() {
        return Optional.ofNullable(principal()).map(User::getId).orElse(null);
    }

    public static User principal() {
        return Optional.ofNullable(authentication())
                .map(Authentication::getPrincipal)
                .map(it -> it instanceof UserDetails ? (User) it : null)
                .orElse(null);
    }

    public static Collection<? extends GrantedAuthority> authorities() {
        return Optional.ofNullable(authentication()).map(Authentication::getAuthorities).orElse(Collections.emptyList());
    }

}
