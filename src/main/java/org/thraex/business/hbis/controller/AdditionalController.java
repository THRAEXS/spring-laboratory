package org.thraex.business.hbis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.Controller;
import org.thraex.business.hbis.entity.Additional;
import org.thraex.business.hbis.service.AdditionalService;

/**
 * @author 鬼王
 * @date 2020/09/07 22:16
 */
@RestController
@RequestMapping("api/hbis/additional")
public class AdditionalController extends Controller<AdditionalService> {

    @GetMapping
    public ResponseEntity<Additional> one() {
        return ResponseEntity.ok(service.one());
    }

    @PostMapping
    public ResponseEntity<Additional> save(@RequestBody Additional additional) {
        service.saveOrUpdate(additional);
        return ResponseEntity.ok(additional);
    }

    @PutMapping
    public ResponseEntity<Additional> put(@RequestBody Additional additional) {
        service.updateById(additional);
        return ResponseEntity.ok(additional);
    }

}
