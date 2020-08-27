package org.thraex.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.thraex.platform.entity.User;

import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/08/27 11:41
 */
public abstract class SecurityHolder {

    public static String id() {
        return Optional.ofNullable(principal()).map(User::getId).orElse(null);
    }

    public static User principal() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(it -> it instanceof UserDetails ? (User) it : null)
                .orElse(null);
    }

}
