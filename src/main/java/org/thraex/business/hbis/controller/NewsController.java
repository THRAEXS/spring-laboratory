package org.thraex.business.hbis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.Controller;
import org.thraex.base.page.Query;
import org.thraex.business.hbis.entity.News;
import org.thraex.business.hbis.service.NewsService;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/07 18:38
 */
@RestController
@RequestMapping("api/hbis/news")
public class NewsController extends Controller<NewsService> {

    @GetMapping
    public ResponseEntity<List<News>> list(Integer type) {
        return ResponseEntity.ok(service.list(type));
    }

    @GetMapping("page")
    public ResponseEntity<Page<News>> page(Query query) {
        return ResponseEntity.ok(service.page(query));
    }

    @PostMapping
    public ResponseEntity<News> save(@RequestBody News news) {
        service.saveOrUpdate(news.snapshot());
        return ResponseEntity.ok(news);
    }

    @PutMapping
    public ResponseEntity<News> update(@RequestBody News news) {
        service.updateById(news.snapshot());
        return ResponseEntity.ok(news);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String id) {
        return ResponseEntity.ok(service.removeById(id));
    }

}
