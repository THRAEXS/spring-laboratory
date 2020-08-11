package org.thraex.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鬼王
 * @date 2020/08/11 11:39
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping
    public String get() {
        return "THRAEX";
    }

}
