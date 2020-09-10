package org.agriculture.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.agriculture.project.entity.Project;
import org.agriculture.project.mapper.ProjectMapper;
import org.agriculture.project.service.ProjectService;

/**
 * @author MLeo
 * @date 2020/09/09 20:28
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
