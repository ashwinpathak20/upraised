package com.upraised.springmvc.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;

/*
 * This model is for Company.
 */
@Entity
@Table(name="Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=3, max=50)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min=3, max=50)
    @Column(name = "company_type")
    private String company_type;

    @NotNull
    @Size(min=3, max=50)
    @Column(name = "location")
    private String location;

    @Column(name = "founder_name")
    private String founder_name;

    @Column(name = "founder_desc")
    private String founder_desc;

    @Column(name = "company_desc")
    private String company_desc;

    @URL(regexp = "^(http://|ftp://|https://)(www).*.*")
    @Column(name = "company_link")
    private String company_link;

    @Column(name = "intro")
    private String intro;

    @Column(name = "domain")
    private String domain;

    @Digits(integer=12, fraction=0)
    @Column(name = "employees_min")
    private int employees_min;

    @Digits(integer=12, fraction=0)
    @Column(name = "employees_max")
    private int employees_max;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="logo")
    private byte[] logo;

    public int getId() {
        return id;
    }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getCompany_type(){ return company_type; }

    public void setCompany_type(String company_type){ this.company_type = company_type; }

    public String getLocation() { return location; }

    public void setLocation(String location){ this.location = location; }

    public String getFounder_name() { return founder_name; }

    public void setFounder_name(String founder_name) { this.founder_name = founder_name; }

    public String getFounder_desc() { return founder_desc; }

    public void setFounder_desc(String founder_desc) { this.founder_desc = founder_desc; }

    public String getCompany_desc() { return company_desc; }

    public void setCompany_desc(String company_desc) { this.company_desc = company_desc; }

    public String getCompany_link() { return company_link; }

    public void setCompany_link(String company_link) { this.company_link = company_link; }

    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    public String getDomain() { return domain; }

    public void setDomain(String domain) {  this.domain = domain; }

    public int getEmployees_min() { return employees_min; }

    public void setEmployees_min(int employees_min) { this.employees_min = employees_min; }

    public int getEmployees_max() { return employees_max; }

    public void setEmployees_max(int employees_max) { this.employees_max = employees_max; }

    public byte[] getLogo() { return logo; }

    public void setLogo(byte[] logo) { this.logo = logo; }
}
