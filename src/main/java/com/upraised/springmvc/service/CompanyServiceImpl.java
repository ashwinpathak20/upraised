package com.upraised.springmvc.service;

import com.upraised.springmvc.dao.CompanyDao;
import com.upraised.springmvc.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.upraised.springmvc.commons.CompanyConstants.COMPANY_SERVICE;

/*
 * This class is responsible for fetching data for Company.
 */
@Component
@Service(COMPANY_SERVICE)
@Transactional
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao dao;

    Logger logger = Logger.getLogger(CompanyServiceImpl.class.getName());

    /*
     * Finds company by id.
     */
    public Company findById(int id) {
        return dao.findById(id);
    }

    /*
     * Saves company instance.
     */
    public void saveCompany(Company company) {
        dao.saveCompany(company);
    }

    /*
     * Deletes company by company name.
     */
    public void deleteCompanyByName(String name) { dao.deleteCompanyByName(name); }

    /*
     * Finds all the companies.
     */
    public List<Company> findAllCompanies() {
        return dao.findAllCompanies();
    }

    /*
     *  This method finds company by name.
     */
    public Company findCompanyByName(String name) {
        Company company = dao.findCompanyByName(name);
        return company;
    }

    /*
     * Checks if company is unique by company_name.
     */
    public boolean isCompanyByNameUnique(Integer id, String name) {
        Company company = findCompanyByName(name);
        return ( company == null || ((id != null) && (company.getId() == id)));
    }

    /*
     * This method lists companies.
     */
    public List<String> listCompaniesByName() {
        try {
            List<Company> companies = findAllCompanies();
            List<String> company_names = new ArrayList<>();
            for (Company company : companies) {
                company_names.add(company.getName());
            }
            return company_names;
        } catch (final Exception e) {
            logger.log(Level.WARNING, "Error in retrieving companies by name");
        }
        return null;
    }
}
