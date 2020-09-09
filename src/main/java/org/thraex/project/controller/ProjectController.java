package org.thraex.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thraex.project.entity.Project;
import org.thraex.project.service.ProjectService;

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

}
