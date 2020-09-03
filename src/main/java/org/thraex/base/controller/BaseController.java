package org.thraex.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thraex.base.entity.Entity;
import org.thraex.base.page.Query;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/28 21:28
 */
public class BaseController<E extends Entity<E>, S extends IService<E>> extends Controller<S> {

    @GetMapping
    public ResponseEntity<List<E>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("{id}")
    public ResponseEntity<E> one(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("page")
    public ResponseEntity<Page<E>> page(Query query) {
        return ResponseEntity.ok(service.page(new Page<>(query.getPage(), query.getSize())));
    }

    @PostMapping
    public ResponseEntity<E> save(@RequestBody E e) {
        service.saveOrUpdate(e.snapshot());
        return ResponseEntity.ok(e);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody E e) {
        return ResponseEntity.ok(service.updateById(e.snapshot()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.removeById(id));
    }

}
