package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPagingationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public Company getCompany(long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    public ResultPagingationDTO getCompanies(Pageable pageable) {
        Page<Company> page = this.companyRepository.findAll(pageable);
        ResultPagingationDTO result = new ResultPagingationDTO();
        Meta meta = new Meta();

        meta.setPage(page.getNumber() + 1);
        meta.setSize(page.getSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());
        result.setMeta(meta);
        result.setResult(page.getContent());

        return result;
    }
}
