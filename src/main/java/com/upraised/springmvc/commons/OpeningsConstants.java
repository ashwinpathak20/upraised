package com.upraised.springmvc.commons;

public class OpeningsConstants {

    public static final String OPENINGS="Openings";
    public static final String JOB_TITLE ="job_title";
    public static final String JOB_ID = "job_id";
    public static final String LOCATION ="location";
    public static final String SENIORITY_LEVEL ="seniority_level";
    public static final String JOB_DESCRIPTION ="job_description";
    public static final String SKILLS_REQUIRED ="skills_required";
    public static final String JOB_LINK ="job_link";
    public static final String SALARY ="salary";
    public static final String COMPANY ="company";
    public static final String REGEX = "^(http://|ftp://|https://)(www).*.*";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_OF_POSTING = "date_of_posting";
    public static final String DATE_TYPE = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate";
    public static final String OPENINGS_SERVICE = "openingsService";
    public static final String OPENINGS_DAO = "openingsDao";
    public static final String DELETE_SQL_QUERY = "delete from Openings where job_id = :job_id";
}
