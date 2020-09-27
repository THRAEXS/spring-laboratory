package org.thraex.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thraex.base.result.Result;
import org.thraex.security.properties.CryptoProperties;

/**
 * @author 鬼王
 * @date 2020/09/27 15:30
 */
@RestController
@RequestMapping("auth")
public class SecurityController {

    @Autowired
    private CryptoProperties crypto;

    @GetMapping("crypto")
    public Result<CryptoProperties> crypto() {
        return Result.ok(crypto);
    }

}
