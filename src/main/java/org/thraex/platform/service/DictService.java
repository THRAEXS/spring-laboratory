package org.thraex.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.thraex.platform.entity.Dict;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/09 19:10
 */
public interface DictService extends IService<Dict> {

    List<Dict> tree();

    String nextLevel(String pid);

    boolean unique(String id, String code);

}
