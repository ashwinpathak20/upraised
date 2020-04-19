package com.upraised.springmvc.dao;

import com.upraised.springmvc.model.Company;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompanyDaoImplTest extends com.upraised.springmvc.dao.EntityDaoImplTest {

    @Autowired
    CompanyDao companyDao;

    @Override
    protected IDataSet getDataSet() throws Exception{
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Company.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(companyDao.findById(1));
        Assert.assertNull(companyDao.findById(3));
    }


    @Test
    public void saveCompany(){
        companyDao.saveCompany(getSampleCompany());
        Assert.assertEquals(companyDao.findAllCompanies().size(), 3);
    }

    @Test
    public void deleteCompanyByName(){
        companyDao.deleteCompanyByName("test_company1");
        Assert.assertEquals(companyDao.findAllCompanies().size(), 1);
    }

    @Test
    public void deleteCompanyByInvalidName(){
        companyDao.deleteCompanyByName("test_company3");
        Assert.assertEquals(companyDao.findAllCompanies().size(), 2);
    }

    @Test
    public void findAllCompanies(){
        Assert.assertEquals(companyDao.findAllCompanies().size(), 2);
    }

    @Test
    public void findCompanyByName(){
        Assert.assertNotNull(companyDao.findCompanyByName("test_company1"));
        Assert.assertNull(companyDao.findCompanyByName("test_company3"));
    }

    public Company getSampleCompany(){
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

        return company1;
    }
}
