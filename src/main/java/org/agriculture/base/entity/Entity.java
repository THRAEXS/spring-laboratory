package org.agriculture.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;
import org.agriculture.security.SecurityHolder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author MLeo
 * @date 2020/08/11 09:52
 */
@Data
@Accessors(chain = true)
public class Entity<C extends Entity<?>> implements Serializable {

    protected String id;

    protected String createBy;

    protected LocalDateTime createTime;

    protected String updateBy;

    protected LocalDateTime updateTime;

    public C setId(String id) {
        this.id = id;
        return (C) this;
    }

    public C setCreateBy(String createBy) {
        this.createBy = createBy;
        return (C) this;
    }

    public C setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return (C) this;
    }

    public C setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return (C) this;
    }

    public C setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return (C) this;
    }

    public C snapshot() {
        LocalDateTime now = LocalDateTime.now();
        String uid = SecurityHolder.id();

        return (C) (StringUtils.hasText(this.id) ?
                this.setUpdateBy(uid).setUpdateTime(now) :
                this.setCreateBy(uid).setCreateTime(now));
    }

}
