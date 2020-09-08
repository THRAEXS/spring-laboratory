package org.hbis.business.hbis.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.hbis.base.controller.Controller;
import org.hbis.business.hbis.entity.Company;
import org.hbis.business.hbis.service.CompanyService;
import org.hbis.platform.service.FileService;
import org.hbis.util.Joiner;

import java.util.Objects;
import java.util.Optional;

/**
 * @author 鬼王
 * @date 2020/09/07 10:14
 */
@RestController
@RequestMapping("api/hbis/companies")
public class CompanyController extends Controller<CompanyService> {

    @Value("${thraex.file.access-prefix}")
    private String accessPrefix;

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<Company> one() {
        return ResponseEntity.ok(service.one());
    }

    @PostMapping
    public ResponseEntity<Company> save(@RequestBody Company company) {
        service.saveOrUpdate(company.snapshot());
        return ResponseEntity.ok(company);
    }

    @PostMapping("upload")
    public ResponseEntity<Company> upload(MultipartFile file, Company company) {
        boolean cidNotBlank = Strings.isNotBlank(company.getCoverId());
        boolean midNotBlank = Strings.isNotBlank(company.getMapId());
        boolean valid = Strings.isNotBlank(company.getId()) && (cidNotBlank || midNotBlank);

        Company c = new Company().setId(company.getId());
        if (valid) {
            Optional.ofNullable(fileService.transfer(file)).ifPresent(f -> {
                String oldFid = cidNotBlank ? company.getCoverId() : company.getMapId();
                Optional.of(oldFid).filter(of -> !Objects.equals(of, "NONE")).ifPresent(of -> fileService.delete(of));

                if (cidNotBlank) {
                    service.updateById(c.setCoverId(f.getId()).setCoverPath(f.getPath()).snapshot());
                    c.setCoverPath(Joiner.path(accessPrefix, c.getCoverPath()));
                }

                if (midNotBlank) {
                    service.updateById(c.setMapId(f.getId()).setMapPath(f.getPath()).snapshot());
                    c.setMapPath(Joiner.path(accessPrefix, c.getMapPath()));
                }
            });
        }

        return ResponseEntity.ok(c);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Company company) {
        return ResponseEntity.ok(service.updateById(company.snapshot()));
    }

}
