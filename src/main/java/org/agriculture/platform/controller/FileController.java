package org.agriculture.platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.agriculture.base.controller.Controller;
import org.agriculture.platform.entity.FileDescriptor;
import org.agriculture.platform.service.FileService;

import java.util.List;

/**
 * @author MLeo
 * @date 2020/09/02 21:36
 */
@RestController
@RequestMapping("api/files")
public class FileController extends Controller<FileService> {

    @GetMapping
    public ResponseEntity<List<FileDescriptor>> list(List<String> ids) {
        return ResponseEntity.ok(service.list(ids));
    }

    @PostMapping
    public ResponseEntity<FileDescriptor> upload(MultipartFile file) {
        return ResponseEntity.ok(service.transfer(file));
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
