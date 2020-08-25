package org.thraex.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.platform.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author 鬼王
 * @date 2020/08/25 10:15
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> list() {
        int size = 123;
        List<User> list = new ArrayList<>(size);
        IntStream.range(0, size).forEach(i -> list.add(User.withId("id" + i).nickname("THRAEX-" + i)
                .username("thraex_" + i).password("111111").roles("admin").disabled(false).build()));

        return ResponseEntity.ok(list);
    }

}
