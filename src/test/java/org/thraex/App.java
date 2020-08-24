package org.thraex;

import org.springframework.security.core.userdetails.UserDetails;
import org.thraex.platform.entity.User;

/**
 * @author 鬼王
 * @date 2020/08/11 11:23
 */
public class App {

    public static void main(String[] args) {
        String[] a = { "USER", "ADMIN", "SUPERADMIN", "MEETING" };
        String[] b = { "USER", "ADMIN", "xxx", "SUPERADMIN", "MEETING" };
        //UserDetails u1 = User.withUsername("admin").password("admin").roles(a).build();
        //
        //String[] b = { "ROLE_USER", "ROLE_ADMIN", "ROLE_SUPERADMIN", "ROLE_MEETING" };
        //List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(b);
        //org.thraex.platform.entity.User user = new org.thraex.platform.entity.User(
        //        "admin", "Admin", "admin", "admin", true, authorityList);
        //System.out.println(u1);
        //System.out.println(user);

        UserDetails build = User.withId("admin").username("admin").password("admin").roles(a).build();
        UserDetails build1 = User.withUsername("hanzo").password("hanzo").roles(b).build();
        System.out.println(1);
    }

}
