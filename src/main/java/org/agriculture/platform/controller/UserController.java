package org.agriculture.platform.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.agriculture.base.page.Query;
import org.agriculture.platform.entity.User;
import org.agriculture.platform.service.UserService;
import org.agriculture.security.SecurityHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author MLeo
 * @date 2020/08/25 10:15
 */
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

    @GetMapping("{id}")
    public ResponseEntity<User> one(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("current")
    public  ResponseEntity<User> current() {
        return ResponseEntity.ok(SecurityHolder.principal());
    }

    @GetMapping("unique")
    public ResponseEntity<Boolean> uniqueness(String id, String username) {
        return ResponseEntity.ok(userService.unique(id, username));
    }

    @PostMapping
    public ResponseEntity<User> save(@Value("${thraex.user.init-password}") String initPassword,
                                     @RequestBody User user) {
        userService.saveOrUpdate(user.setUsername(user.getUsername().toLowerCase())
                .setPassword(passwordEncoder.encode(initPassword)).snapshot());
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody User user) {
        BiConsumer<String, Runnable> toNull = (v, r) -> { if (Strings.isBlank(v)) { r.run(); } };
        toNull.accept(user.getUsername(), () -> user.setUsername(null));
        toNull.accept(user.getNickname(), () -> user.setNickname(null));
        toNull.accept(user.getPassword(), () -> user.setPassword(null));

        user.setPassword(Optional.ofNullable(user.getPassword()).map(p -> passwordEncoder.encode(p)).orElse(null));

        return ResponseEntity.ok(userService.updateById(user.snapshot()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(userService.removeById(id));
    }

}
