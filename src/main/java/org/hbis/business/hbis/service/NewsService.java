package org.hbis.business.hbis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.hbis.base.page.Query;
import org.hbis.business.hbis.entity.News;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/07 09:00
 */
public interface NewsService extends IService<News> {

    List<News> list(long size);

    List<News> list(Integer type);

    Page<News> page(Query query);

}
