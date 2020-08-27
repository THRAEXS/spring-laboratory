package org.thraex.platform.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.page.Query;
import org.thraex.platform.entity.User;
import org.thraex.platform.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author 鬼王
 * @date 2020/08/25 10:15
 */
@Log4j2
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> list(Query query) {
        final int total = 123;
        Object value = query.getValue();
        int page = Math.toIntExact(query.getPage());
        int size = Math.toIntExact(query.getSize());

        List<User> list = new ArrayList<>(size);
        IntStream.range(0, size > total ? total : size).forEach(i -> {
            int index = (page - 1) * size + (i + 1);
            list.add(User.withId("id" + index).nickname("THRAEX-" + index)
                    .username("thraex_" + index).password("111111")
                    .roles("ADMIN").disabled(false).build());
        });

        Map<String, Object> result = new HashMap<>(2);
        result.put("total", total);
        result.put("data", list);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        log.info("Save user...");
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{enable}")
    public ResponseEntity<Boolean> enabled(@PathVariable boolean enable) {
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(true);
    }

}
