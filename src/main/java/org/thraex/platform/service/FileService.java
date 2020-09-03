package org.thraex.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.platform.entity.FileDescriptor;

import java.util.List;

/**
 * @author 鬼王
 * @date 2020/09/02 21:40
 */
public interface FileService extends IService<FileDescriptor> {

    List<FileDescriptor> list(List<String> ids);

    FileDescriptor transfer(MultipartFile file);

    List<FileDescriptor> transfer(List<MultipartFile> files);

    boolean delete(String id);

}
