package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.thraex.base.page.Query;
import org.thraex.business.hbis.entity.News;
import org.thraex.business.hbis.mapper.NewsMapper;
import org.thraex.business.hbis.service.NewsService;

import java.util.List;
import java.util.Objects;

/**
 * @author 鬼王
 * @date 2020/09/07 09:02
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public List<News> list(long size) {
        return this.list(Wrappers.<News>lambdaQuery()
                .orderByDesc(News::getCreateTime).last(size > 0, "LIMIT " + size));
    }

    @Override
    public List<News> list(Integer type) {
        return this.list(Wrappers.<News>lambdaQuery()
                .eq(Objects.nonNull(type), News::getType, type)
                .orderByDesc(News::getCreateTime));
    }

    @Override
    public Page<News> page(Query query) {
        News news = query.parse(News.class);

        Page<News> page = this.page(new Page<>(query.getPage(), query.getSize()), Wrappers.<News>lambdaQuery()
                .eq(Objects.nonNull(news.getType()), News::getType, news.getType())
                .or()
                .like(Strings.isNotBlank(news.getTitle()), News::getTitle, news.getTitle())
                .or()
                .like(Strings.isNotBlank(news.getAuthor()), News::getAuthor, news.getAuthor())
                .orderByDesc(News::getCreateTime, News::getUpdateTime));

        return page;
    }

}
