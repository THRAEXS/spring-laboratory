package org.hbis.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 鬼王
 * @date 2020/09/02 21:55
 */
public class Controller<S extends IService<?>> {

    @Autowired
    protected S service;

}
