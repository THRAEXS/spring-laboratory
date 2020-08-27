package org.thraex.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Optional;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<User>> list(Query query) {
        String kw = Optional.ofNullable(query.getValue()).map(it -> it.toString()).orElse(null);
        boolean notBlank = Strings.isNotBlank(kw);

        Page<User> page = userService.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<User>lambdaQuery()
                        .like(notBlank, User::getNickname, kw).or().like(notBlank, User::getUsername, kw)
                        .orderByAsc(User::getUsername, User::getNickname, User::getCreateTime));

        return ResponseEntity.ok(page);
    }

    @GetMapping("mock")
    public ResponseEntity<Map<String, Object>> mock(Query query) {
        final int total = 123;
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
        log.info("Save user: {}", userService.saveOrUpdate(
                user.setPassword(passwordEncoder.encode(user.getPassword())).snapshot()));
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
