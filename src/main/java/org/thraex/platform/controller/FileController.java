package org.thraex.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.base.controller.Service;
import org.thraex.platform.entity.FileDescriptor;
import org.thraex.platform.service.FileService;

import java.util.Arrays;
import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/02 21:36
 */
@RestController
@RequestMapping("api/files")
public class FileController extends Service<FileService> {

    @PostMapping
    public ResponseEntity<List<FileDescriptor>> upload(MultipartFile file) {
        return uploads(Arrays.asList(file));
    }

    @PostMapping("multiple")
    public ResponseEntity<List<FileDescriptor>> uploads(List<MultipartFile> files) {
        return ResponseEntity.ok(service.transfer(files));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
