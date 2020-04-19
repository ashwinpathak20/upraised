package com.upraised.springmvc.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.upraised.springmvc.model.Filters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.upraised.springmvc.model.Openings;
import com.upraised.springmvc.model.Company;
import com.upraised.springmvc.service.OpeningsService;
import com.upraised.springmvc.service.CompanyService;

public class AppControllerTest {

    @Mock
    OpeningsService openingsService;

    @Mock
    CompanyService companyService;

    @Mock
    MessageSource message;

    @InjectMocks
    AppController appController;

    @Spy
    List<Company> companies = new ArrayList<Company>();

    @Spy
    List<Openings> openings = new ArrayList<Openings>();

    @Spy
    List<String> companyNames = new ArrayList<String>();

    @Spy
    Filters filters;

    @Spy
    ModelMap model;

    @Mock
    BindingResult result;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        companies = getCompanyList();
        openings = getOpeningsList();
        companyNames = getCompanyListByName();
        filters = getFilters();
    }

    @Test
    public void getCompanies(){
        when(companyService.listCompaniesByName()).thenReturn(companyNames);
        Assert.assertEquals(appController.getCompanies(model), "listCompanies");
        Assert.assertEquals(model.get("companies"), companyNames);
        verify(companyService, atLeastOnce()).listCompaniesByName();
    }

    @Test
    public void newCompany(){
        Assert.assertEquals(appController.newCompany(model), "registrationcompanies");
        Assert.assertNotNull(model.get("company"));
        Assert.assertFalse((Boolean)model.get("edit"));
        Assert.assertEquals(((Company)model.get("company")).getId(), 0);
    }


    @Test
    public void saveCompanyWithValidationError(){
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(companyService).saveCompany(any(Company.class));
        Assert.assertEquals(appController.saveCompany(companies.get(0), result, model), "registrationcompanies");
    }

    @Test
    public void saveCompanyWithValidationErrorNonUniqueCompany(){
        when(result.hasErrors()).thenReturn(false);
        when(companyService.isCompanyByNameUnique(anyInt(), anyString())).thenReturn(false);
        Assert.assertEquals(appController.saveCompany(companies.get(0), result, model), "registrationcompanies");
    }

    @Test
    public void saveCompanyWithValidationErrorNonCorrectEmployee(){
        when(result.hasErrors()).thenReturn(false);
        when(companyService.isCompanyByNameUnique(anyInt(), anyString())).thenReturn(true);
        Assert.assertEquals(appController.saveCompany(companies.get(1), result, model), "registrationcompanies");
    }

    @Test
    public void saveCompanyWithSuccess(){
        when(result.hasErrors()).thenReturn(false);
        when(companyService.isCompanyByNameUnique(anyInt(), anyString())).thenReturn(true);
        doNothing().when(companyService).saveCompany(any(Company.class));
        Assert.assertEquals(appController.saveCompany(companies.get(0), result, model), "redirect:/companies");
        Assert.assertEquals(model.get("success"), "Company test_company1 registered successfully");
    }

    @Test
    public void getCompanyAndOpeningsByName(){
        Company company = companies.get(0);
        when(companyService.findCompanyByName(anyString())).thenReturn(company);
        when(openingsService.findOpeningsByCompany(anyString())).thenReturn(openings);
        Assert.assertEquals(appController.getCompanyAndOpeningsByName(company.getName(), model), "companyInfoAndOpenings");
        Assert.assertNotNull(model.get("company"));
        Assert.assertNotNull(model.get("openings"));
        Assert.assertEquals(((Company)model.get("company")).getId(), 0);
        verify(openingsService, atLeastOnce()).findOpeningsByCompany(company.getName());
    }

    @Test
    public void getOpeningsByFilterWithValidationError(){
        when(result.hasErrors()).thenReturn(true);
        Assert.assertEquals(appController.getOpeningsByFilter(filters, result, model), "companyInfoAndOpenings");
    }

    @Test
    public void getOpeningsByFilterWithSuccess(){
        Company company = companies.get(0);
        when(result.hasErrors()).thenReturn(false);
        when(companyService.findCompanyByName(anyString())).thenReturn(company);
        when(openingsService.findOpeningsByCompany(anyString())).thenReturn(openings);
        Assert.assertEquals(appController.getOpeningsByFilter(filters, result, model), "companyInfoAndOpenings");
        Assert.assertNotNull(model.get("company"));
        Assert.assertNotNull(model.get("openings"));
        Assert.assertEquals(((Company)model.get("company")).getId(), 0);
    }

    @Test
    public void listOpenings(){
        when(openingsService.findAllOpenings()).thenReturn(openings);
        Assert.assertEquals(appController.listOpenings(model), "allopenings");
        Assert.assertEquals(model.get("openings"), openings);
        verify(openingsService, atLeastOnce()).findAllOpenings();
    }

    @Test
    public void newOpenings() {
        Assert.assertEquals(appController.newOpenings(model), "registrationopenings");
        Assert.assertNotNull(model.get("openings"));
        Assert.assertFalse((Boolean)model.get("edit"));
        Assert.assertEquals(((Openings)model.get("openings")).getId(), 0);
    }

    @Test
    public void saveOpeningsWithValidationError() {
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(openingsService).saveOpenings(any(Openings.class));
        Assert.assertEquals(appController.saveOpenings(openings.get(0), result, model), "registrationopenings");
    }

    @Test
    public void saveOpeningsWithValidationErrorNonUniqueJobId(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(false);
        Assert.assertEquals(appController.saveOpenings(openings.get(0), result, model), "registrationopenings");
    }

    @Test
    public void saveOpeningsWithValidationErrorExistingCompany(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(true);
        when(companyService.findCompanyByName(anyString())).thenReturn(null);
        Assert.assertEquals(appController.saveOpenings(openings.get(0), result, model), "registrationopenings");
    }

    @Test
    public void saveOpeningsWithValidationErrorInvalidSeniority(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(true);
        when(companyService.findCompanyByName(anyString())).thenReturn(null);
        Assert.assertEquals(appController.saveOpenings(openings.get(0), result, model), "registrationopenings");
    }

    @Test
    public void saveOpeningsWithSuccess(){
        Company company = companies.get(0);
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(true);
        when(companyService.findCompanyByName(anyString())).thenReturn(company);
        Assert.assertEquals(appController.saveOpenings(openings.get(1), result, model), "redirect:/openings");
        Assert.assertEquals(model.get("success"), "Opening 2 registered successfully");
    }

    @Test
    public void editCompanyandTitle(){
        Openings opening = openings.get(0);
        when(openingsService.findOpeningsByJobId(anyInt())).thenReturn(opening);
        Assert.assertEquals(appController.editCompanyandTitle(anyInt(), model), "registrationopenings");
        Assert.assertNotNull(model.get("openings"));
        Assert.assertTrue((Boolean)model.get("edit"));
        Assert.assertEquals(((Openings)model.get("openings")).getId(), 0);
    }

    @Test
    public void updateOpeningsWithValidationError(){
        when(result.hasErrors()).thenReturn(true);
        doNothing().when(openingsService).updateOpenings(any(Openings.class));
        Assert.assertEquals(appController.updateOpenings(openings.get(0), result, model,0), "registrationopenings");
    }

    @Test
    public void updateOpeningsWithValidationErrorNonUniqueJobId(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(false);
        Assert.assertEquals(appController.updateOpenings(openings.get(0), result, model, 0), "registrationopenings");
    }

    @Test
    public void updateOpeningsWithValidationErrorInvalidSeniority(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(true);
        Assert.assertEquals(appController.saveOpenings(openings.get(0), result, model), "registrationopenings");
    }

    @Test
    public void updateOpeningsWithSuccess(){
        when(result.hasErrors()).thenReturn(false);
        when(openingsService.isOpeningsByJobIdUnique(anyInt(), anyInt())).thenReturn(true);
        doNothing().when(openingsService).updateOpenings(any(Openings.class));
        Assert.assertEquals(appController.updateOpenings(openings.get(1), result, model, 0), "redirect:/openings");
        Assert.assertEquals(model.get("success"), "Opening 2 updated successfully");
    }


    @Test
    public void deleteOpening(){
        doNothing().when(openingsService).deleteOpeningsByJobId(anyInt());
        Assert.assertEquals(appController.deleteOpening(1), "redirect:/allopenings");
    }

    public Filters getFilters(){
        Filters filters = new Filters();
        filters.setCompany("test_company1");
        filters.setJob_title("test_title1");
        filters.setSalary("123");
        return filters;
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

    public List<Openings> getOpeningsList(){
        Openings openings1 = new Openings();
        openings1.setJob_id(1);
        openings1.setSeniority_level("test_sl_1");
        openings1.setLocation("test_location1");
        openings1.setSalary(BigDecimal.valueOf(100.0));
        openings1.setSkills_required("test_skills1");
        openings1.setJob_title("test_title1");
        openings1.setCompany("test_company1");
        openings1.setJob_link("https://www.test_company1.com");

        Openings openings2 = new Openings();
        openings2.setJob_id(2);
        openings2.setSeniority_level("senior");
        openings2.setLocation("test_location2");
        openings2.setSalary(BigDecimal.valueOf(101.0));
        openings2.setSkills_required("test_skills2");
        openings2.setJob_title("test_title2");
        openings2.setCompany("test_company2");
        openings2.setJob_link("https://www.test_company2.com");

        openings.add(openings1);
        openings.add(openings2);
        return openings;
    }

    public List<String> getCompanyListByName(){
        companyNames.add(companies.get(0).getName());
        companyNames.add(companies.get(1).getName());
        return companyNames;
    }
}