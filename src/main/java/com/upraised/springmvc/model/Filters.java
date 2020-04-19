package com.upraised.springmvc.model;

public class Filters {

    private String location;

    private String job_title;

    private String company;

    private String salary;

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCompany() { return  company; }

    public void setCompany(String company) { this.company = company; }

    public String getSalary() { return salary; }

    public void setSalary(String salary) { this.salary = salary; }
}
