package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.business.hbis.mapper.AdvertMapper;
import org.thraex.business.hbis.service.AdvertService;
import org.thraex.business.hbis.vo.AdvertVO;
import org.thraex.platform.entity.FileDescriptor;
import org.thraex.platform.service.FileService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2020/09/03 17:16
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements AdvertService {

    @Autowired
    private FileService fileService;

    @Override
    public List<AdvertVO> listVO() {
        List<Advert> adverts = this.list();
        List<FileDescriptor> files = fileService.list(adverts.stream().map(Advert::getFid).collect(Collectors.toList()));

        List<AdvertVO> vos = files.stream()
                .map(f -> new AdvertVO(
                        adverts.stream()
                                .filter(a -> f.getId().equals(a.getFid()))
                                .map(Advert::getId).findFirst().orElse(null),
                        f.getId(), f.getName(), f.getPath()))
                .filter(v -> Objects.nonNull(v.getId()))
                .collect(Collectors.toList());

        return vos;
    }

}
