package org.agriculture.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.agriculture.platform.entity.Dict;
import org.agriculture.platform.service.DictService;

import java.util.List;

/**
 * @author MLeo
 * @date 2020/09/09 19:11
 */
@RestController
@RequestMapping("api/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping
    public ResponseEntity<List<Dict>> list() {
        return ResponseEntity.ok(dictService.list());
    }

    @GetMapping("tree")
    public ResponseEntity<List<Dict>> tree() {
        return ResponseEntity.ok(dictService.tree());
    }

    @GetMapping("{id}")
    public ResponseEntity<Dict> one(@PathVariable String id) {
        return ResponseEntity.ok(dictService.getById(id));
    }

    @GetMapping("down/{identifier}")
    public ResponseEntity<List<Dict>> down(@PathVariable String identifier) {
        Dict parent = this.parent(identifier);

        return ResponseEntity.ok(dictService.list(Wrappers.<Dict>lambdaQuery()
                .likeRight(Dict::getLevelCode, parent.getLevelCode()).orderByAsc(Dict::getLevelCode)));
    }

    @GetMapping("children/{identifier}")
    public ResponseEntity<List<Dict>> children(@PathVariable String identifier) {
        Dict parent = this.parent(identifier);

        return ResponseEntity.ok(dictService.list(Wrappers.<Dict>lambdaQuery()
                .eq(Dict::getPid, parent.getId()).orderByAsc(Dict::getLevelCode)));
    }

    @GetMapping("unique")
    public ResponseEntity<Boolean> uniqueness(String id, String code) {
        return ResponseEntity.ok(dictService.unique(id, code));
    }

    @PostMapping
    public ResponseEntity<Dict> save(@RequestBody Dict dict) {
        if (Strings.isBlank(dict.getPid())) { dict.setPid(null); }

        if (Strings.isBlank(dict.getId())) {
            dict.setLevelCode(dictService.nextLevel(dict.getPid()));
        }

        dictService.saveOrUpdate(dict.snapshot());
        return ResponseEntity.ok(dict);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Dict dict) {
        if (Strings.isBlank(dict.getPid())) { dict.setPid(null); }

        return ResponseEntity.ok(dictService.updateById(dict.snapshot()));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String levelCode) {
        return ResponseEntity.ok(dictService.remove(Wrappers.<Dict>lambdaQuery().likeRight(Dict::getLevelCode, levelCode)));
    }

    private Dict parent(String identifier) {
        return dictService.getOne(Wrappers.<Dict>lambdaQuery()
                .eq(Dict::getId, identifier).or().eq(Dict::getCode, identifier));
    }

}
