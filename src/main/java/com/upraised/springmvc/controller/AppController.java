package com.upraised.springmvc.controller;

import com.upraised.springmvc.model.Company;
import com.upraised.springmvc.model.Filters;
import com.upraised.springmvc.model.Openings;
import com.upraised.springmvc.service.CompanyService;
import com.upraised.springmvc.service.OpeningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMethod;
import com.upraised.springmvc.util.FileValidator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.NestedServletException;

import javax.validation.Valid;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    OpeningsService openingsService;

    @Autowired
    CompanyService companyService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    /*
     * This method will list all existing companies.
     */
    @RequestMapping(value = { "/companies" }, method = RequestMethod.GET)
    public String getCompanies(ModelMap model) {
        List<String> companies = companyService.listCompaniesByName();
        model.addAttribute("companies", companies);
        return "listCompanies";
    }

    /*
     * This method will get company by name.
     */
    @RequestMapping(value = { "/company-{name}" }, method = RequestMethod.GET)
    public String getCompanyAndOpeningsByName(@PathVariable String name, ModelMap model) {
        Company company = companyService.findCompanyByName(name);
        List<Openings> openings = openingsService.findOpeningsByCompany(name);
        Filters filters = new Filters();
        model.addAttribute("filters", filters);
        model.addAttribute("company", company);
        model.addAttribute("openings", openings);
        return "companyInfoAndOpenings";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * applying filters in openings. It also validates the user input.
     */
    @RequestMapping(value = { "/company-{name}" }, method = RequestMethod.POST)
    public String getOpeningsByFilter(@Valid Filters filters, BindingResult result,
                          ModelMap model) {

        if (result.hasErrors()) {
            return "companyInfoAndOpenings";
        }

        Map<String, String> map = new HashMap<>();
        map.put("job_title", filters.getJob_title());
        map.put("location", filters.getLocation());
        map.put("company", filters.getCompany());
        map.put("salary", filters.getSalary());
        List<Openings> openings = openingsService.filterOpenings(map);
        Company company = companyService.findCompanyByName(filters.getCompany());
        model.addAttribute("company", company);
        model.addAttribute("openings", openings);
        return "companyInfoAndOpenings";
    }

    /*
     * This method will provide the medium to add a new company.
     */
    @RequestMapping(value = { "/newCompany" }, method = RequestMethod.GET)
    public String newCompany(ModelMap model) {
        Company company = new Company();
        model.addAttribute("company", company);
        model.addAttribute("edit", false);
        return "registrationcompanies";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving company in database. It also validates the user input
     */
    @RequestMapping(value = { "/newCompany" }, method = RequestMethod.POST)
    public String saveCompany(@Valid Company company, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registrationcompanies";
        }

        if(!companyService.isCompanyByNameUnique(company.getId(), company.getName())){
            FieldError error =new FieldError("company","name", messageSource.getMessage("non.unique.company", new String[]{company.getName()}, Locale.getDefault()));
            result.addError(error);
            return "registrationcompanies";
        }
        companyService.saveCompany(company);
        model.addAttribute("success", "Company " + company.getName() + " registered successfully");
        return "listCompanies";
    }

    /*
     * This method will list all existing openings.
     */
    @RequestMapping(value = { "/openings" }, method = RequestMethod.GET)
    public String listOpenings(ModelMap model) {

        List<Openings> openings = openingsService.findAllOpenings();
        model.addAttribute("openings", openings);
        return "allopenings";
    }

    /*
     * This method will provide the medium to add a new openings.
     */
    @RequestMapping(value = { "/newOpening" }, method = RequestMethod.GET)
    public String newOpenings(ModelMap model) {
        Openings openings = new Openings();
        model.addAttribute("openings", openings);
        model.addAttribute("edit", false);
        return "registrationopenings";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving openings in database. It also validates the user input
     */
    @RequestMapping(value = { "/newOpening" }, method = RequestMethod.POST)
    public String saveOpenings(@Valid Openings openings, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registrationopenings";
        }

        if(!openingsService.isOpeningsByJobIdUnique(openings.getId(), openings.getJob_id())){
            FieldError error =new FieldError("openings","job_id", messageSource.getMessage("non.unique.job_id", new String[]{(String.valueOf(openings.getJob_id()))}, Locale.getDefault()));
            result.addError(error);
            return "registrationopenings";
        }

        Company company = companyService.findCompanyByName(openings.getCompany());
        if(company==null){
            FieldError error =new FieldError("openings","company", messageSource.getMessage("non.existing.company", new String[]{(String.valueOf(openings.getCompany()))}, Locale.getDefault()));
            result.addError(error);
            return "registrationopenings";
        }

        openingsService.saveOpenings(openings);
        model.addAttribute("success", "Opening " + openings.getJob_id() + " registered successfully");
        return "allopenings";
    }

    /*
     * This method will provide the medium to update an existing openings.
     */
    @RequestMapping(value = { "/edit-{job_id}-opening" }, method = RequestMethod.GET)
    public String editCompanyandTitle(@PathVariable Integer job_id, ModelMap model) {
        Openings openings = openingsService.findOpeningsByJobId(job_id);
        model.addAttribute("openings", openings);
        model.addAttribute("edit", true);
        return "registrationopenings";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating openings in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-{job_id}-opening" }, method = RequestMethod.POST)
    public String updateOpenings(@Valid Openings openings, BindingResult result,
                                 ModelMap model, @PathVariable Integer job_id) {

        if (result.hasErrors()) {
            return "registrationopenings";
        }

        if(!openingsService.isOpeningsByJobIdUnique(openings.getId(), openings.getJob_id())){
            FieldError error =new FieldError("openings","job_id", messageSource.getMessage("non.unique.openings", new Integer[]{openings.getJob_id()}, Locale.getDefault()));
            result.addError(error);
            return "registrationopenings";
        }

        openingsService.updateOpenings(openings);

        model.addAttribute("success", "Opening " + openings.getJob_id() + " updated successfully");
        return "allopenings";
    }


    /*
     * This method will delete an opening by it's job_id value.
     */
    @RequestMapping(value = { "/delete-{job_id}-opening" }, method = RequestMethod.GET)
    public String deleteOpening(@PathVariable Integer job_id) {
        openingsService.deleteOpeningsByJobId(job_id);
        return "redirect:/allopenings";
    }
}
