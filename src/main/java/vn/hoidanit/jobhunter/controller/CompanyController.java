package vn.hoidanit.jobhunter.controller;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.ResultPagingationDTO;
import vn.hoidanit.jobhunter.service.CompanyService;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        Company res = this.companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/companies")
    public ResponseEntity<ResultPagingationDTO> getCompanies(@RequestParam("page") Optional<String> cureOptional,
            @RequestParam("size") Optional<String> sizeOptional) {
        int page = cureOptional.isPresent() ? Integer.parseInt(cureOptional.get()) : 1;
        int size = sizeOptional.isPresent() ? Integer.parseInt(sizeOptional.get()) : 10;

        Pageable pageable = PageRequest.of(page - 1, size);

        ResultPagingationDTO res = this.companyService.getCompanies(pageable);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        Company res = this.companyService.getCompany(id);
        return ResponseEntity.ok().body(res);
    }
}
