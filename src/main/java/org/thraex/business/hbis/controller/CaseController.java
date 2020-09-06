package org.thraex.business.hbis.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.base.controller.Controller;
import org.thraex.business.hbis.entity.Case;
import org.thraex.business.hbis.service.CaseService;
import org.thraex.platform.service.FileService;
import org.thraex.util.Joiner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2020/09/04 16:17
 */
@RestController
@RequestMapping("api/hbis/cases")
public class CaseController extends Controller<CaseService> {

    @Value("${thraex.file.access-prefix}")
    private String accessPrefix;

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<Case>> list() {
        return ResponseEntity.ok(service.list(Wrappers.<Case>lambdaQuery().orderByAsc(Case::getCreateTime))
                .stream().map(it -> it.setCover(Joiner.path(accessPrefix, it.getCover()))).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Case> save(MultipartFile file, Case c) {
        Optional.ofNullable(fileService.transfer(file)).ifPresent(f -> service.save(c.setFid(f.getId()).setCover(f.getPath()).snapshot()));
        return ResponseEntity.ok(c);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String id, @RequestParam String fid) {
        boolean a = fileService.delete(fid);
        boolean b = service.removeById(id);
        return ResponseEntity.ok(a && b);
    }

}
