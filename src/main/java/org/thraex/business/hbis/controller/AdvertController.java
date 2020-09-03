package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.base.controller.Controller;
import org.thraex.business.hbis.entity.Advert;
import org.thraex.business.hbis.service.AdvertService;
import org.thraex.business.hbis.vo.AdvertVO;
import org.thraex.platform.entity.FileDescriptor;
import org.thraex.platform.service.FileService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 鬼王
 * @date 2020/09/03 14:05
 */
@RestController
@RequestMapping("api/adverts")
public class AdvertController extends Controller<AdvertService> {

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<AdvertVO>> list() {
        List<Advert> adverts = service.list();
        List<FileDescriptor> files = fileService.list(adverts.stream().map(Advert::getFid).collect(Collectors.toList()));

        List<AdvertVO> vos = files.stream()
                .map(f -> new AdvertVO(
                        adverts.stream()
                                .filter(a -> f.getId().equals(a.getFid()))
                                .map(Advert::getId).findFirst().orElse(null),
                        f.getId(), f.getName(), f.getPath()))
                .filter(v -> Objects.nonNull(v.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(vos);
    }

    @PostMapping
    public ResponseEntity<Advert> upload(MultipartFile file) {
        return ResponseEntity.ok(Optional.ofNullable(fileService.transfer(file))
                .map(f -> {
                    Advert a = new Advert(f.getId());
                    service.save(a);
                    return a;
                }).orElse(null));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(Optional.ofNullable(service.getById(id))
                .map(a -> fileService.delete(a.getFid()) && service.removeById(a.getId()))
                .orElse(false));
    }

}
