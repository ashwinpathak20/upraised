package com.upraised.springmvc.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/*
 * This model stores the openings information.
 */
@Entity
@Table(name="Openings")
public class Openings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Digits(integer=12, fraction=0)
    @Column(name = "job_id")
    private Integer job_id;

    @NotNull
    @Size(min=3, max=50)
    @Column(name = "job_title", nullable = false)
    private String job_title;

    @Column(name = "seniority_level")
    private String seniority_level;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "job_description")
    private String job_description;

    @Column(name = "skills_required")
    private String skills_required;

    @Column(name = "job_link")
    @URL(regexp = "^(http://|ftp://|https://)(www).*.*")
    private String job_link;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "date_of_posting", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate postingDate;

    @Digits(integer=12, fraction=2)
    @Column(name = "salary")
    private BigDecimal salary;

    @NotNull
    @Size(min=1, max=50)
    @Column(name = "company", nullable = false)
    private String company;

    public int getId() {
        return id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() { return job_title; }

    public void setJob_title(String job_title) {this.job_title = job_title; }

    public String getSeniority_level() { return seniority_level; }

    public void setSeniority_level(String seniority_level) { this.seniority_level = seniority_level; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getJob_description() { return job_description; }

    public void setJob_description(String job_description) { this.job_description = job_description; }

    public String getSkills_required() { return skills_required; }

    public void setSkills_required(String skills_required) { this.skills_required = skills_required; }

    public String getJob_link() { return job_link; }

    public void setJob_link(String job_link) { this.job_link = job_link; }

    public LocalDate getPostingDate() { return postingDate; }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
