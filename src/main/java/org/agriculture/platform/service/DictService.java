package org.agriculture.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.agriculture.platform.entity.Dict;

import java.util.List;

/**
 * @author MLeo
 * @date 2020/09/09 19:10
 */
public interface DictService extends IService<Dict> {

    List<Dict> tree();

    String nextLevel(String pid);

    boolean unique(String id, String code);

}
