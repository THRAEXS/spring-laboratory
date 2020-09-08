package org.hbis.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.io.Files;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.hbis.constant.DateTimeFormat;
import org.hbis.platform.entity.FileDescriptor;
import org.hbis.platform.mapper.FileMapper;
import org.hbis.platform.service.FileService;
import org.hbis.util.Joiner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/09/02 22:21
 */
@Log4j2
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDescriptor> implements FileService {

    @Value("${thraex.file.directory}")
    private String directory;

    @Value("${thraex.file.access-prefix}")
    private String accessPrefix;

    @Override
    public List<FileDescriptor> list(List<String> ids) {
        return Optional.ofNullable(ids)
                .filter(it -> !it.isEmpty())
                .map(it -> this.list(Wrappers.<FileDescriptor>lambdaQuery()
                        .in(FileDescriptor::getId, it).orderByDesc(FileDescriptor::getCreateTime)))
                .map(it -> it.parallelStream())
                .map(it -> it.map(f -> f.setPath(Joiner.path(accessPrefix, f.getPath())))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public FileDescriptor transfer(MultipartFile file) {
        return transfer(Arrays.asList(file)).stream().findFirst().orElse(null);
    }

    @Override
    public List<FileDescriptor> transfer(List<MultipartFile> files) {
        log.debug("Start transferring files to disk");

        Stream<MultipartFile> stream = Optional.ofNullable(files).map(it -> it.parallelStream()).orElse(Stream.empty());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.DATE.value());
        final String today = formatter.format(LocalDate.now());

        List<FileDescriptor> list = new ArrayList<>();
        stream.forEach(f -> {
            String name = f.getOriginalFilename();
            String type = f.getContentType();
            long size = f.getSize();

            String id = UUID.randomUUID().toString().replace("-", "");
            try {
                String newName = String.format("%s.%s", id, Files.getFileExtension(name));
                String path = Joiner.path(today, newName);

                String fullDir = Joiner.path(directory, today);
                Path fullPath = Paths.get(Joiner.path(directory, today, newName));

                log.debug("File transfer: [{}, {}, {}]", name, type, size);
                java.nio.file.Files.createDirectories(Paths.get(fullDir));
                Path file = java.nio.file.Files.createFile(fullPath);
                f.transferTo(file);
                log.debug("Save to: [{}]", fullPath);

                list.add(new FileDescriptor(name, path, type, size).snapshot().setId(id));
            } catch (IOException e) {
                log.error(e.toString());
            }
        });

        this.saveBatch(list);

        log.debug("End of transmission");

        return list;
    }

    @Override
    public boolean delete(final String id) {
        FileDescriptor file = this.getById(id);
        return Optional.ofNullable(file).map(f -> {
            log.debug("Delete file: [{}, {}]", f.getName(), f.getPath());
            boolean a = false;
            try {
                a = java.nio.file.Files.deleteIfExists(Paths.get(Joiner.path(directory, file.getPath())));
            } catch (IOException e) {
                log.error(e.toString());
            }

            boolean b = this.removeById(id);
            log.debug("Physically deleted: [{}], record delete: [{}]", a, b);

            return a && b;
        }).orElseGet(() -> {
            log.debug("File not found");
            return false;
        });
    }

}
