package com.upraised.springmvc.dao;

import com.upraised.springmvc.model.Company;

import java.util.List;

public interface CompanyDao {

    Company findById(int id);

    void saveCompany(Company company);

    void deleteCompanyByName(String name);

    List<Company> findAllCompanies();

    Company findCompanyByName(String name);
}
