package org.agriculture.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.agriculture.platform.entity.Dict;
import org.agriculture.platform.mapper.DictMapper;
import org.agriculture.platform.service.DictService;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author MLeo
 * @date 2020/09/09 19:10
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> tree() {
        return toTree(this.list(Wrappers.<Dict>lambdaQuery().orderByAsc(Dict::getLevelCode)));
    }

    @Override
    public String nextLevel(String pid) {
        Dict parent = Strings.isBlank(pid) ? null : this.getById(pid);

        boolean pn = Objects.isNull(parent);
        List<Dict> list = this.list(Wrappers.<Dict>lambdaQuery()
                .isNull(pn, Dict::getPid).eq(!pn, Dict::getPid, pid)
                .orderByDesc(Dict::getLevelCode));

        final String empty = "";
        final String format = "%08d";
        String pl = Optional.ofNullable(parent).map(Dict::getLevelCode).orElse(empty);
        String max = list.stream().findFirst()
                .map(it -> it.getLevelCode().substring(it.getLevelCode().length() - 8))
                .orElse(String.format(format, 0));
        String next = String.format(format, Integer.valueOf(max) + 1);

        return String.format("%s%s", pl, next);
    }

    @Override
    public boolean unique(String id, String code) {
        int count = this.count(Wrappers.<Dict>lambdaQuery()
                .eq(Dict::getCode, code).ne(Strings.isNotBlank(id), Dict::getId, id));
        return count > 0 ? false : true;
    }

    private List<Dict> toTree(List<Dict> list) {
        // TODO: Optimize
        List<Dict> roots = list.parallelStream()
                .filter(it -> Strings.isBlank(it.getPid()))
                .sorted((m1, m2) -> m2.getLevelCode().compareTo(m1.getLevelCode()))
                .collect(Collectors.toList());

        List<Dict> leafs = list.parallelStream()
                .filter(it -> Strings.isNotBlank(it.getPid()))
                .collect(Collectors.toList());

        roots.forEach(r -> toTree(r, leafs));

        return roots;
    }

    private void toTree(Dict root, List<Dict> nodes) {
        List<Dict> children = nodes.parallelStream()
                .filter(l -> root.getId().equals(l.getPid()))
                .sorted(Comparator.comparing(Dict::getLevelCode))
                .collect(Collectors.toList());
        root.setChildren(children);
        children.forEach(n -> toTree(n, nodes));
    }

}
