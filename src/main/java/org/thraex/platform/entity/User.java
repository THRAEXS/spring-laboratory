package org.thraex.platform.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.thraex.base.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Reference {@link org.springframework.security.core.userdetails.User}
 *
 * @author 鬼王
 * @date 2020/08/24 10:13
 */
public class User extends Entity<User> implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String nickname;

    private String username;

    private String password;

    private boolean enabled;

    private Set<GrantedAuthority> authorities;

    public User(String id, String nickname, String username, String password, boolean enabled,
                Collection<? extends GrantedAuthority> authorities) {
        Assert.noNullElements(Arrays.asList(username, password),
                "Cannot pass null or empty values to constructor");

        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");

        Comparator<GrantedAuthority> comparator = (g1, g2) -> Objects.isNull(g2.getAuthority()) ? -1
                : Objects.isNull(g1.getAuthority()) ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(comparator);

        authorities.forEach(it -> {
            Assert.notNull(it, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(it);
        });

        return sortedAuthorities;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User ? Objects.equals(username, ((User) o).username) : false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Id: ").append(this.id).append("; ");
        sb.append("Nickname: ").append(this.nickname).append("; ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");

        sb.append(authorities.isEmpty() ? "Not granted any authorities" : String.format("Granted Authorities: %s",
                authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","))));

        return sb.toString();
    }

    public static UserBuilder withId(String id) {
        return builder().id(id);
    }

    public static UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private String id;
        private String nickname;
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean disabled;
        private Function<String, String> passwordEncoder = Function.identity();

        private UserBuilder() { }

        public UserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(
                    roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), () ->
                        String.format("%s cannot start with ROLE_ (it is automatically added)", role));
                authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role)));
            }
            return authorities(authorities);
        }

        public UserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public UserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = this.passwordEncoder.apply(password);
            return new User(id, nickname, username, encodedPassword, !disabled, authorities);
        }

    }

}
