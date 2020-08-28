package org.thraex.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.controller.BaseController;
import org.thraex.platform.entity.Role;
import org.thraex.platform.service.RoleService;

/**
 * @author 鬼王
 * @date 2020/08/28 21:18
 */
@RestController
@RequestMapping("api/role")
public class RoleController extends BaseController<Role, RoleService> {

    //@GetMapping
    //public ResponseEntity<List<Role>> list(Query query) {
    //    String kw = Optional.ofNullable(query.getValue()).map(it -> it.toString()).orElse(null);
    //    boolean notBlank = Strings.isNotBlank(kw);
    //
    //    return ResponseEntity.ok(service.list(Wrappers.<Role>lambdaQuery()
    //            .like(notBlank, Role::getName, kw).or().like(notBlank, Role::getCode, kw)
    //            .orderByAsc(Role::getName, Role::getCode).orderByDesc(Role::getCreateTime)));
    //}

    @GetMapping("unique")
    public ResponseEntity<Boolean> uniqueness(String id, String code) {
        return ResponseEntity.ok(service.unique(id, code));
    }

}
