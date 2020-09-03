package org.thraex.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.platform.entity.FileDescriptor;
import org.thraex.platform.mapper.FileMapper;
import org.thraex.platform.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/09/02 22:21
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDescriptor> implements FileService {

    @Value("${thraex.file.directory}")
    private String directory;

    @Override
    public List<FileDescriptor> list(List<String> ids) {
        return Optional.ofNullable(ids)
                .filter(it -> !it.isEmpty())
                .map(it -> this.list(Wrappers.<FileDescriptor>lambdaQuery()
                        .in(FileDescriptor::getId, it).orderByDesc(FileDescriptor::getCreateTime)))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<FileDescriptor> transfer(List<MultipartFile> files) {
        Stream<MultipartFile> stream = Optional.ofNullable(files).map(it -> it.parallelStream()).orElse(Stream.empty());

        List<FileDescriptor> list = new ArrayList<>();
        stream.forEach(f -> {
            try {
                f.transferTo(Paths.get(directory + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

}
