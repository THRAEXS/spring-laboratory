package org.agriculture.project.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.agriculture.base.page.Query;
import org.agriculture.platform.entity.Dict;
import org.agriculture.platform.service.DictService;
import org.agriculture.project.entity.Project;
import org.agriculture.project.service.ProjectService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author MLeo
 * @date 2020/09/09 20:26
 */
@Controller
@RequestMapping({ "project", "api/projects" })
public class ProjectController extends org.agriculture.base.controller.Controller<ProjectService> {

    private static List<Dict> DICT_INDUSTRY;
    private static List<Dict> DICT_STAGE;
    private static List<Dict> DICT_FUND;

    public ProjectController(DictService dictService) {
        DICT_INDUSTRY = dictService.list(Wrappers.<Dict>lambdaQuery().likeRight(Dict::getCode, "DICT_INDUSTRY_"));
        DICT_STAGE = dictService.list(Wrappers.<Dict>lambdaQuery().likeRight(Dict::getCode, "DICT_STAGE_"));
        DICT_FUND = dictService.list(Wrappers.<Dict>lambdaQuery().likeRight(Dict::getCode, "DICT_FUND_"));
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute(id);
        return "project/edit";
    }

    @Data
    private class SmallBox {

        private String name;
        private long count;
        private String proportion;

        private SmallBox(String name, long count, double proportion) {
            this.name = name;
            this.count = count;
            this.proportion = String.format("%.2f", proportion);
        }

    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        List<Project> all = service.list();
        int size = all.size();
        Double ds = Double.valueOf(size);

        Map<String, DoubleSummaryStatistics> industryInvestment = all.parallelStream().collect(
                Collectors.groupingBy(Project::getIndustry, Collectors.summarizingDouble(p -> p.getInvestment().doubleValue())));

        Map<String, Object> bars = new HashMap<>(2);
        List<Dict> industryDict = industryInvestment.keySet().stream()
                .map(k -> DICT_INDUSTRY.stream().filter(d -> d.getId().equals(k)).findFirst().orElse(null))
                .sorted(Comparator.comparing(Dict::getLevelCode))
                .collect(Collectors.toList());
        bars.put("labels", industryDict.stream().map(Dict::getName).collect(Collectors.toList()));
        List<Double> sum = industryDict.stream().map(Dict::getId)
                .map(i -> industryInvestment.get(i)).map(s -> s.getSum()).collect(Collectors.toList());
        bars.put("sum", sum);
        model.addAttribute("bars", bars);

        Map<String, Long> stage = all.parallelStream().collect(
                Collectors.groupingBy(Project::getStage, Collectors.counting()));

        List<SmallBox> boxes = stage.keySet().parallelStream()
                .map(s -> DICT_STAGE.stream().filter(d -> d.getId().equals(s)).findFirst().orElse(null))
                .sorted(Comparator.comparing(Dict::getLevelCode))
                .map(d -> new SmallBox(d.getName(), stage.get(d.getId()), (stage.get(d.getId()) / ds) * 100))
                .collect(Collectors.toList());
        model.addAttribute("boxes", boxes);

        Map<Long, DoubleSummaryStatistics> yearInvestment = all.parallelStream().collect(
                Collectors.groupingBy(Project::getYear, Collectors.summarizingDouble(p -> p.getInvestment().doubleValue())));

        List<Long> year = yearInvestment.keySet().stream().sorted().collect(Collectors.toList());
        Map<String, Object> lines = new HashMap<>(2);
        lines.put("year", year);
        lines.put("sum", year.stream().map(y -> yearInvestment.get(y).getSum()).collect(Collectors.toList()));
        model.addAttribute("lines", lines);

        return "project/dashboard";
    }

    @RequestMapping("mock")
    @ResponseBody
    public List<Project> mock(Integer size) {
        int diSize = DICT_INDUSTRY.size();
        int dsSize = DICT_STAGE.size();
        int dfSize = DICT_FUND.size();

        int step = 10000;
        Integer total = Optional.ofNullable(size).map(s -> s * step).orElse(step);

        List<Project> list = new ArrayList<>(total);
        IntStream.range(0, total).forEach(i -> {
            int index = i + 1;
            LocalDateTime now = LocalDateTime.now();
            int year = (2010 + new Random().nextInt(11) + 1);
            double v = new Random().nextDouble() * 100;
            Project item = new Project(
                    "项目编号-" + index,
                    "项目名称-" + index,
                    DICT_INDUSTRY.get(new Random().nextInt(diSize)).getId(),
                    DICT_STAGE.get(new Random().nextInt(dsSize)).getId(),
                    BigDecimal.valueOf(Double.valueOf(String.format("%.2f", (new Random().nextDouble() * 100)))),
                    DICT_FUND.get(new Random().nextInt(dfSize)).getId(),
                    Long.valueOf(year),
                    "工期建设单位-" + index,
                    "审批文号-" + index,
                    "审批机关-" + index,
                    "建设地点-" + index,
                    "所属地区-" + index,
                    "主管单位-" + index,
                    "项目负责人-" + index,
                    "1000070"
            );
            list.add(item.snapshot().setCreateTime(now.withYear(year)));
        });

        service.saveBatch(list, total/5);

        return list;
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.withYear(2018));
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
