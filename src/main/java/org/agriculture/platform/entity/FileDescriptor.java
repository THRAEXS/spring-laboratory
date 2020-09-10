package org.agriculture.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.agriculture.base.entity.Entity;

/**
 * @author MLeo
 * @date 2020/09/02 21:33
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FileDescriptor extends Entity<FileDescriptor> {

    private String name;

    private String path;

    /**
     * {@link org.springframework.http.MediaType}
     */
    private String contentType;

    private long size;

    public FileDescriptor() { }

    public FileDescriptor(String name, String path, String contentType, long size) {
        this.name = name;
        this.path = path;
        this.contentType = contentType;
        this.size = size;
    }

}
