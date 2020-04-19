package com.upraised.springmvc.dao;

import com.upraised.springmvc.model.Company;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.upraised.springmvc.commons.CompanyConstants.*;

@Component
@Repository(COMPANY_DAO)
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao{

    public Company findById(int id) {
        return getByKey(id);
    }

    public void saveCompany(Company company) {
        persist(company);
    }

    public void deleteCompanyByName(String name) {
        Query query = getSession().createSQLQuery(DELETE_SQL_QUERY);
        query.setString(NAME, name);
        query.executeUpdate();
    }

    /*
     * This method finds all the existing companies.
     */
    @SuppressWarnings("unchecked")
    public List<Company> findAllCompanies() {
        Criteria criteria = createEntityCriteria();
        return (List<Company>) criteria.list();
    }

    /*
     * This method finds a company by name.
     */
    public Company findCompanyByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(NAME, name));
        return (Company) criteria.uniqueResult();
    }
}
