package org.thraex.project.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thraex.base.page.Query;
import org.thraex.project.entity.Project;
import org.thraex.project.service.ProjectService;

import java.util.Objects;

/**
 * @author 鬼王
 * @date 2020/09/09 20:26
 */
@Controller
@RequestMapping({ "project", "api/projects" })
public class ProjectController extends org.thraex.base.controller.Controller<ProjectService> {

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute(id);
        return "project/edit";
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Project>> page(Query query) {
        Project p = query.parse(Project.class);

        Page<Project> page = service.page(new Page<>(query.getPage(), query.getSize()),
                Wrappers.<Project>lambdaQuery()
                        .like(Strings.isNotBlank(p.getItemNo()), Project::getItemNo, p.getItemNo())
                        .like(Strings.isNotBlank(p.getName()), Project::getName, p.getName())
                        .eq(Strings.isNotBlank(p.getIndustry()), Project::getIndustry, p.getIndustry())
                        .eq(Strings.isNotBlank(p.getStage()), Project::getStage, p.getStage())
                        .eq(Strings.isNotBlank(p.getFund()), Project::getFund, p.getFund())
                        .eq(Objects.nonNull(p.getYear()), Project::getYear, p.getYear())
                        .orderByDesc(Project::getCreateTime).orderByAsc(Project::getItemNo, Project::getName));

        return ResponseEntity.ok(page);
    }

    @GetMapping("project/{id}")
    @ResponseBody
    public ResponseEntity<Project> one(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Project> save(@RequestBody Project project) {
        service.save(project.snapshot());
        return ResponseEntity.ok(project);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Project> update(@RequestBody Project project) {
        service.updateById(project.snapshot());
        return ResponseEntity.ok(project);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Boolean> delete(@RequestParam String id) {
        return ResponseEntity.ok(service.removeById(id));
    }

}
