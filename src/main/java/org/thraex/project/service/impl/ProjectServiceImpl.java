package org.thraex.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.project.entity.Project;
import org.thraex.project.mapper.ProjectMapper;
import org.thraex.project.service.ProjectService;

/**
 * @author 鬼王
 * @date 2020/09/09 20:28
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
}
