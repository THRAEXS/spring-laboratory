package org.thraex.platform.entity;

import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author 鬼王
 * @date 2020/08/24 11:05
 */
public class UserExtends extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nickname;

    private Integer sex;

    protected String createBy;

    protected LocalDateTime createTime;

    protected String updateBy;

    protected LocalDateTime updateTime;

    public UserExtends(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getId() {
        return id;
    }

    public UserExtends setId(String id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserExtends setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserExtends setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public UserExtends setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserExtends setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public UserExtends setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserExtends setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
