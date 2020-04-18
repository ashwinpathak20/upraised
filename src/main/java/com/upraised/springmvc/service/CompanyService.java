package com.upraised.springmvc.service;

import com.upraised.springmvc.model.Company;

import java.util.List;

public interface CompanyService {

    Company findById(int id);

    void saveCompany(Company company);

    void deleteCompanyByName(String name);

    List<Company> findAllCompanies();

    Company findCompanyByName(String name);

    boolean isCompanyByNameUnique(Integer id, String name);

    List<String> listCompaniesByName();
}
