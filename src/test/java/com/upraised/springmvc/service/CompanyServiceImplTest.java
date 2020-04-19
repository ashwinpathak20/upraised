package com.upraised.springmvc.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import com.upraised.springmvc.model.Company;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.upraised.springmvc.dao.CompanyDao;

public class CompanyServiceImplTest {

    @Mock
    CompanyDao dao;

    @InjectMocks
    CompanyServiceImpl companyService;

    @Spy
    List<Company> companies = new ArrayList<Company>();

    @Spy
    List<String> companyNames = new ArrayList<String>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        companies = getCompanyList();
        companyNames = getCompanyListByName();
    }

    @Test
    public void findById(){
        Company company = companies.get(0);
        when(dao.findById(anyInt())).thenReturn(company);
        Assert.assertEquals(companyService.findById(company.getId()), company);
    }

    @Test
    public void saveCompany(){
        doNothing().when(dao).saveCompany(any(Company.class));
        companyService.saveCompany(any(Company.class));
        verify(dao, atLeastOnce()).saveCompany(any(Company.class));
    }

    @Test
    public void deleteCompanyByName(){
        doNothing().when(dao).deleteCompanyByName(anyString());
        companyService.deleteCompanyByName(anyString());
        verify(dao, atLeastOnce()).deleteCompanyByName(anyString());
    }

    @Test
    public void findAllCompanies(){
        when(dao.findAllCompanies()).thenReturn(companies);
        Assert.assertEquals(companyService.findAllCompanies(),companies);
    }

    @Test
    public void findCompanyByName(){
        Company company = companies.get(0);
        when(dao.findCompanyByName(anyString())).thenReturn(company);
        Assert.assertEquals(companyService.findCompanyByName(anyString()), company);
    }

    @Test
    public void isCompanyByNameUnique(){
        Company company = companies.get(0);
        when(dao.findCompanyByName(anyString())).thenReturn(company);
        Assert.assertEquals(companyService.isCompanyByNameUnique(company.getId(), company.getName()), true);
    }

    @Test
    public void listCompaniesByNameWithError(){
        when(companyService.findAllCompanies()).thenReturn(null);
        Assert.assertEquals(companyService.listCompaniesByName(), null);
    }

    @Test
    public void listCompaniesByName(){
        when(companyService.findAllCompanies()).thenReturn(companies);
        Assert.assertEquals(companyService.listCompaniesByName(), companyNames);
    }

    public List<Company> getCompanyList(){
        Company company1 = new Company();
        company1.setName("test_company1");
        company1.setCompany_desc("test_description1");
        company1.setCompany_link("https://www.test_company1.com");
        company1.setCompany_type("test_type1");
        company1.setDomain("test_seller1");
        company1.setFounder_name("test_founder1");
        company1.setFounder_desc("test_desc1");
        company1.setIntro("test_intro1");
        company1.setEmployees_max(10);
        company1.setEmployees_min(5);
        company1.setLocation("test_location1");

        Company company2 = new Company();
        company2.setName("test_company2");
        company2.setCompany_desc("test_description2");
        company2.setCompany_link("https://www.test_company2.com");
        company2.setCompany_type("test_type2");
        company2.setDomain("test_seller2");
        company2.setFounder_name("test_founder2");
        company2.setFounder_desc("test_desc2");
        company2.setIntro("test_intro2");
        company2.setEmployees_max(10);
        company2.setEmployees_min(20);
        company2.setLocation("test_location2");

        companies.add(company1);
        companies.add(company2);
        return companies;
    }

    public List<String> getCompanyListByName(){
        companyNames.add(companies.get(0).getName());
        companyNames.add(companies.get(1).getName());
        return companyNames;
    }
}