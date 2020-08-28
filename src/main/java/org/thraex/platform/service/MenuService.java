package org.thraex.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.thraex.platform.entity.Menu;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/08/19 16:40
 */
public interface MenuService extends IService<Menu> {

    List<Menu> mock();

    List<Menu> tree();

    String nextLevel(String pid);

    boolean unique(String id, String code);

}
