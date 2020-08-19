package org.thraex.platform.service;

import org.thraex.platform.entity.Menu;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/19 16:40
 */
public interface MenuService {

    List<Menu> list();

    List<Menu> tree();

}
