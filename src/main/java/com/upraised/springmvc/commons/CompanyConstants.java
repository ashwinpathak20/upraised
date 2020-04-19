package com.upraised.springmvc.commons;

public class CompanyConstants {

    public static final String TABLE_COMPANY="Company";
    public static final String NAME ="name";
    public static final String COMPANY_TYPE ="company_type";
    public static final String LOCATION ="location";
    public static final String FOUNDER_NAME ="founder_name";
    public static final String FOUNDER_DESC ="founder_desc";
    public static final String COMPANY_DESC ="company_desc";
    public static final String COMPANY_LINK ="company_link";
    public static final String INTRO ="intro";
    public static final String DOMAIN ="domain";
    public static final String EMPLOYEES_MIN ="employees_min";
    public static final String EMPLOYEES_MAX ="employees_max";
    public static final String LOGO = "logo";
    public static final String REGEX = "^(http://|ftp://|https://)(www).*.*";
    public static final String COMPANY_SERVICE = "companyService";
    public static final String COMPANY_DAO = "companyDao";
    public static final String DELETE_SQL_QUERY = "delete from Company where name = :name";
}
