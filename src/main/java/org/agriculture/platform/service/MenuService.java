package org.agriculture.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.agriculture.platform.entity.Menu;

import java.util.List;

/**
 * @author MLeo
 * @date 2020/08/19 16:40
 */
public interface MenuService extends IService<Menu> {

    List<Menu> mock();

    List<Menu> list(String code);

    List<Menu> tree();

    List<Menu> tree(List<String> ids);

    List<Menu> tree(String code);

    String nextLevel(String pid);

    boolean unique(String id, String code);

}
