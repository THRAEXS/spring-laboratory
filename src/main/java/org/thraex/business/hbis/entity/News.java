package org.thraex.business.hbis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.thraex.base.entity.Entity;

/**
 * @author 鬼王
 * @date 2020/09/06 21:54
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class News extends Entity<News> {

    private String title;

    private String author;

    private long hits;

    private String content;

    /**
     * 0: company; 10: industry
     */
    private int type;

}
