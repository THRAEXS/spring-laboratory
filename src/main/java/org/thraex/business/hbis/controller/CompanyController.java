package org.thraex.business.hbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thraex.base.controller.Controller;
import org.thraex.business.hbis.entity.Company;
import org.thraex.business.hbis.service.CompanyService;
import org.thraex.platform.service.FileService;

/**
 * @author 鬼王
 * @date 2020/09/07 10:14
 */
@RestController
@RequestMapping("api/hbis/companies")
public class CompanyController extends Controller<CompanyService> {

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
    public ResponseEntity<Boolean> upload(MultipartFile file, Company company) {
        //Optional.ofNullable(fileService.transfer(file)).ifPresent(f -> {
        //    System.out.println(company);
        //});
        return ResponseEntity.ok(false);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Company company) {
        return ResponseEntity.ok(service.updateById(company.snapshot()));
    }

}
